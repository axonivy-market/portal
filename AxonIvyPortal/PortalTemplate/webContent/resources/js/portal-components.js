$(document).ready(function() {
	portalDropdown.bindEventTriggerIcon();
	portalAutoComplete.bindEventTriggerIcon();

	// Trigger icon change when autocomplete finish ajax call
	var $this = this;
	$(document).on('pfAjaxComplete.' + this.id, function(e, xhr, settings) {
		portalAutoComplete.changeIcon();
	});
});

/**
 * Behavior controller of dropdown in Portal.
 */
var portalDropdown = (function PortalDropdown() {
	var selectedDropdownId = null;
	function getSelectedDropdown(selectNode) {
		var $dropdowns = $('.ui-selectonemenu-panel');
		if (selectNode) {
			selectedDropdownId = selectNode.id + '_panel';
		}
		var $selectedDropdown = null;
		for (var i = 0; i < $dropdowns.length; i++) {
			if ($dropdowns.get(i).id === selectedDropdownId) {
				$selectedDropdown = $($dropdowns.get(i));
				break;
			}
		}
		return $selectedDropdown;
	}
	function updateTriggerIcon(selectNode) {
		if (selectNode == undefined) {
			return;
		}
		var $dropdownTriggerIcon = $(selectNode).find('.ui-selectonemenu-trigger').find('.ui-icon');
		var $selectedDropdown = getSelectedDropdown(selectNode);
		if ($selectedDropdown.css('display') !== 'none') {
			$dropdownTriggerIcon.not('.rotate-icon').addClass('rotate-icon');
			$('.ui-selectonemenu-trigger').find('.ui-icon').not($dropdownTriggerIcon).removeClass('rotate-icon');
		} else {
			$dropdownTriggerIcon.removeClass('rotate-icon');
		}
	}
	function bindEventUpdateTriggerIconState() {
		$('.ui-selectonemenu').click(function() {
			updateTriggerIcon(this);
		});
	}
	function bindEventTriggerIcon() {
		$('body')
				.click(
						function(event) {
							var $targetNode = $(event.target);

							var isClickOutside = (!$targetNode.hasClass('ui-selectonemenu') 
									&& $targetNode.closest('.ui-selectonemenu').length == 0
									&& !$targetNode.hasClass('ui-selectonemenu-items-wrapper'));
							var isClickOnDropdownContent = $targetNode.hasClass('ui-selectonemenu-list');

							if (isClickOutside && !isClickOnDropdownContent) {
								// When user click outside the dropdown, reset all trigger icon state
								$('.ui-selectonemenu').find('.ui-selectonemenu-trigger').find('.ui-icon').removeClass('rotate-icon');
							} else {
								// When user click on dropdown, update trigger icon's state of selecting dropdown
								if (!$targetNode.hasClass('ui-selectonemenu')) {
									$targetNode = $targetNode.closest('.ui-selectonemenu');
								}
								updateTriggerIcon($targetNode.get(0));
							}
							// bind event for all active trigger icons
							bindEventUpdateTriggerIconState();
						});
	}
	return {
		/**
		 * Bind event for all active dropdowns to update their trigger icon state
		 */
		bindEventTriggerIcon : bindEventTriggerIcon
	};
})();

var portalAutoComplete = (function() {
	function changeIcon() {
		var $autoCompletePanels = $('.ui-autocomplete-panel');
		if ($autoCompletePanels.length > 0) {
			for (var i = 0; i < $autoCompletePanels.length; i++) {
				var $autoCompletePanel = $($('.ui-autocomplete-panel').get(i));
				var $autoCompleteComponent = $('#' + $autoCompletePanel.attr('id').replace('_panel', '').replace(/:/g, '\\:'));
				var $autoCompleteDropdownButton = $autoCompleteComponent.find('button.ui-autocomplete-dropdown');
				if ($autoCompletePanel.css('display') != 'none') {
					$autoCompleteDropdownButton.addClass('panel-visible');
				} else {
					$autoCompleteDropdownButton.removeClass('panel-visible');
				}
			}
		}
	}
	function bindEventTriggerIcon() {
		$('body').click(function(event) {
			changeIcon()
		});
		$('body').keyup(function(event) {
			changeIcon()
		});
	}
	return {
		/**
		 * Bind event for all active dropdowns to update their trigger icon state
		 */
		changeIcon: changeIcon,
		bindEventTriggerIcon : bindEventTriggerIcon
	};
})();