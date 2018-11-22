'use strict';

(function() {

  var socket = io();
  var canvas = document.getElementsByClassName('whiteboard')[0];
  canvas.width = canvas.offsetWidth;
  canvas.height = canvas.offsetHeight;
  var colors = document.getElementsByClassName('color');
  var context = canvas.getContext('2d');

  var prevX = 0, prevY = 0, currX = 0, currY = 0;
   
  var current = {
    color: 'black'
  };
  var drawing = false;
  
  if (context) {
    canvas.addEventListener('mousedown', onMouseDown, false);
    canvas.addEventListener('mouseup', onMouseUp, false);
    canvas.addEventListener('mouseout', onMouseUp, false);
    canvas.addEventListener('mousemove', throttle(onMouseMove, 10), false);
  }

  for (var i = 0; i < colors.length; i++){
    colors[i].addEventListener('click', onColorUpdate, false);
  }

  socket.on('drawing', onDrawingEvent);

  window.addEventListener('resize', onResize, false);
  onResize();

  function drawLine(x0, y0, x1, y1, color, emit){
    context.beginPath();
    context.moveTo(x0, y0);
    context.lineTo(x1, y1);
    context.strokeStyle = color;
    context.lineWidth = 4;
    context.stroke();
    context.closePath();

    if (!emit) { return; }
    var w = canvas.width;
    var h = canvas.height;

    socket.emit('drawing', {
      x0: x0 / w,
      y0: y0 / h,
      x1: x1 / w,
      y1: y1 / h,
      color: color
    });
  }

  function onMouseDown(e){
    drawing = true;

    prevX = currX;
    prevY = currY;
   // drawLine(prevX, prevY, prevX, prevY, current.color,true);
   var pos = getPos(e);
   currX = pos.x;
   currY = pos.y;
   context.fillStyle = current.color;
   context.arc(currX, currY, 2.5, 0, 2*Math.PI, false);
   context.fill()
  }

  function onMouseUp(e){
    if (!drawing) { return; }
    drawing = false;
  }

  function onMouseMove(e){
    if (!drawing) { return; }

    prevX = currX;
    prevY = currY;
    var pos = getPos(e);
    currX = pos.x;
    currY = pos.y;
    drawLine(prevX, prevY, currX, currY, current.color,true);
  }

  function onColorUpdate(e){
    current.color = e.target.className.split(' ')[1];
  }

  
  // limit the number of events per second
  function throttle(callback, delay) {
    var previousCall = new Date().getTime();
    return function() {
      var time = new Date().getTime();

      if ((time - previousCall) >= delay) {
        previousCall = time;
        callback.apply(null, arguments);
      }
    };
  }

  function onDrawingEvent(data){
    var w = canvas.width;
    var h = canvas.height;
    drawLine(data.x0 * w, data.y0 * h, data.x1 * w, data.y1 * h, data.color);
  }

  // make the canvas fill its parent
  function onResize() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
  }

  function clearCanvas(canvas, context) {
    context.clearRect(0, 0, canvas.width, canvas.height);
  }

  function getPos(e) {
    var rect = canvas.getBoundingClientRect();
    var x = 2 * (e.clientX - rect.left);
    var y = 2 *(e.clientY - rect.top);

    return {x, y}
  }
})();