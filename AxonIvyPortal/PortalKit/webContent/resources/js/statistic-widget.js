function barChartExtender() {
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
    fontSize : '11.4px'
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
}