function barChartExtender() {
  this.cfg.grid = {
    gridLineColor : 'transparent',
    background : 'rgba(255,255,255, 0.2)',
    drawBorder : false,
    shadow : false
  };
  this.cfg.axes.yaxis.labelOptions = {
    textColor : '#ffffff',
    fontSize : '11.4px',
  };
  this.cfg.axes.xaxis.labelOptions = {
    textColor : '#ffffff',
    fontSize : '11.4px'
  };
  this.cfg.axes.xaxis.tickOptions = {
    textColor : '#ffffff',
    fontSize : '11.4px'
  };
  this.cfg.axes.yaxis.tickOptions = {
    textColor : '#ffffff',
    fontSize : '11.4px'
  };
}

function chartExtender() {
  this.cfg.grid = {
    background : 'rgba(255,255,255, 0.2)',
    drawGridlines: false,
    drawBorder : false,
    shadow: false
  };
}