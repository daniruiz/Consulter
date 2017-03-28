(function($) {
  $.fn.timepicki = function(options) {
    var defaults = {increase_direction:"up", custom_classes:"", min_hour_value:00, max_hour_value:23, step_size_hours:"1", step_size_minutes:"5", overflow_minutes:true, disable_keyboard_mobile:true, on_change:null};
    var settings = $.extend({}, defaults, options);
    return this.each(function() {
			var ele = $(this);
			var ele_hei = ele.outerHeight();
			ele_hei -= 10;
			$(ele).wrap("<div class='time_pick'>");
			var ele_par = $(this).parents(".time_pick");
			var new_ele = $(
				"<div class='timepicker_wrap " + settings.custom_classes + "'>" +
					"<div class='arrow_top'></div>" +
					"<div class='time'><div class='prev action-next'></div><div class='ti_tx'><input type='text' class='timepicki-input' disabled></div><div class='next action-prev'></div></div>" +
					"<div class='mins'><div class='prev action-next'></div><div class='mi_tx'><input type='text' class='timepicki-input' disabled></div><div class='next action-prev'></div></div>");
			ele_par.append(new_ele);
			var ele_next = $(this).next(".timepicker_wrap");
			var ele_next_all_child = ele_next.find("div");
			var inputs = ele_par.find('input');

			// open or close time picker when clicking
			$(document).on("click", function(event) {
                if (!$(event.target).is(ele) && !is_element_in_timepicki($(event.target))) {
						close_timepicki();
                } else {
                    var ele_lef =  -72;

                    ele_next.css({
                        "top": ele_hei+ "px",
                        "left":  ele_lef+ "px"
                    });
                    open_timepicki();
                }
			});
        
			// open the modal when the user focuses on the input
			ele.on('focus', open_timepicki);
        

			// select all text in input when user focuses on it
			inputs.on('focus', function() {
				var input = $(this);
				if (!input.is(ele)) {
					input.select();
				}
			});

			// close the modal when the time picker loses keyboard focus
			inputs.on('blur', function() {
				setTimeout(function() {
					var focused_element = $(document.activeElement);
					if (focused_element.is(':input') && !is_element_in_timepicki(focused_element)) {
						close_timepicki();
					}
				}, 0);
			});

			function is_element_in_timepicki(jquery_element) {
				return $.contains(ele_par[0], jquery_element[0]) || ele_par.is(jquery_element);
			}

			function open_timepicki() {
				set_date(settings.start_time);
				ele_next.show();
				// focus on the first input and select its contents
				var first_input = ele_next.find('input:visible').first();
				first_input.focus();
				// if the user presses shift+tab while on the first input,
				// they mean to exit the time picker and go to the previous field
				var first_input_exit_handler = function(e) {
					if (e.which === 9 && e.shiftKey) {
						first_input.off('keydown', first_input_exit_handler);
						var all_form_elements = $(':input:visible:not(.timepicki-input)');
						var index_of_timepicki_input = all_form_elements.index(ele);
						var previous_form_element = all_form_elements.get(index_of_timepicki_input-1);
						previous_form_element.focus();
					}
				};
				first_input.on('keydown', first_input_exit_handler);
			}

			function close_timepicki() {
				ele_next.hide();
			}

			function set_date(startTime) {
                var ti = ele.data('timepicki-tim'),
                        mi = ele.data('timepicki-mini');
                if(ti == undefined || mi == undefined){
                    ti = startTime[0];
                    mi = startTime[1];
                    ele.data('timepicki-tim', ti);
                    ele.data('timepicki-mini', mi);
                }
                ele_next.find(".ti_tx input").val(ti);
				ele_next.find(".mi_tx input").val(mi);
			}

		});
	};

}(jQuery));