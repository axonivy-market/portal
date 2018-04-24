function barChartExtender() {
  var currentAngle;
  if (window.screen.availWidth < 1366) {
	  currentAngle = -30;
  } else {
	  currentAngle = 0;
  }
  this.cfg.grid = {
    gridLineColor : 'transparent',
    background : 'rgba(255,255,255, 0)',
    drawBorder : true,
    shadow : false
  };
  this.cfg.axes.yaxis.labelOptions = {
    textColor : 'black',
    fontSize : '11.4px',
  };
  this.cfg.axes.xaxis.labelOptions = {
    textColor : 'black',
    fontSize : '11.4px'
  };
  this.cfg.axes.xaxis.tickOptions = {
    textColor : 'black',
    fontSize : '11.4px',
    angle: currentAngle
  };
  this.cfg.axes.yaxis.tickOptions = {
    textColor : 'black',
    fontSize : '11.4px'
  };
}

function chartExtender() {
  this.cfg.grid = {
    background : 'rgba(255,255,255, 0)',
    drawGridlines: false,
    drawBorder : false,
    shadow: false
  };
  this.cfg.seriesDefaults.rendererOptions.dataLabelFormatString = '%.4s%%';
  this.cfg.seriesDefaults.rendererOptions.dataLabelThreshold = 0;
}

function elapsedTimeChartExtender() {
	  this.cfg.grid = {
	    background : 'rgba(255,255,255, 0)',
	    drawGridlines: false,
	    drawBorder : false,
	    shadow: false
	  };
	  this.cfg.seriesDefaults.rendererOptions.dataLabelFormatString = '%.4s%%';
	  this.cfg.seriesDefaults.rendererOptions.dataLabelThreshold = 0;
	  this.cfg.highlighter = {
	    show: true,
	    useAxesFormatters: false,
	    tooltipContentEditor: function(ev, seriesIndex, pointIndex, plot) {
	        var seconds = plot.series[seriesIndex].data[pointIndex][1];

	        var days = Math.floor(seconds / (3600*24));
	        seconds -= days*3600*24;
	        var hours = Math.floor(seconds / 3600);
	        seconds -= hours*3600;
	        var minutes = Math.floor(seconds / 60);
	        seconds -= minutes*60;
	        var result = seconds + " seconds";
	        if (minutes > 0) {
	        	result = minutes + " minutes - " + result;
	        }
	        if (hours > 0) {
	        	result = hours + " hours - " + result;
	        }
	        if (days > 0) {
	        	result = days + " days - " + result;
	        }
	        return result;
	    }
	  }
	}