$(function () {
  setTimeout(()=>{
    responsiveRelatedTaskTable();
  }, 0);
})

function responsiveRelatedTaskTable() {
  let relatedTaskTable = document.getElementById("case-details-related-task-table");
  if(relatedTaskTable) {
    let headerArr = relatedTaskTable.getElementsByTagName("th");
    if (!headerArr || headerArr.length === 0) {
      return;
    }
  
    for (let i = headerArr.length - 3; i > 0; i--) {
      hideColumnWhenTextInColumnNotEnoughWidth(headerArr[i], i);
    }
  }
}

function hideColumnWhenTextInColumnNotEnoughWidth(element, index) {
  removeStyle(element, "width");
  let currentWidth = element.getBoundingClientRect().width;
  element.style.display = "initial";
  let acceptedWidth = element.firstElementChild.getBoundingClientRect().width + 2 * +window.getComputedStyle(element).paddingLeft.replace("px", "");
  removeStyle(element, "display");
  if (currentWidth < acceptedWidth) {

    if(index === 1) {
      element.style.width = "200px";
    } else {
      element.style.width = 0;
    }
    
  }
}

$(window).resize(() => {
  responsiveRelatedTaskTable();
});
