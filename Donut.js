var el = document.getElementById("c");
var ctx = el.getContext("2d");
var start = null;

var tmr1 = undefined, tmr2 = undefined;
var A=1, B=1;

  var R1 = 1;
  var R2 = 2;
  var K1 = 150;
  var K2 = 5;
  var canvasframe=function() {
    ctx.fillStyle='#000';
    ctx.fillRect(0, 0, ctx.canvas.width, ctx.canvas.height);

    if(tmr1 === undefined) {
      A += 0.07;
      B += 0.03;
    }
    var cA=Math.cos(A), sA=Math.sin(A),
        cB=Math.cos(B), sB=Math.sin(B);
    for(var j=0;j<6.28;j+=0.3) { 
      var ct=Math.cos(j),st=Math.sin(j); 
      for(i=0;i<6.28;i+=0.1) {
        var sp=Math.sin(i),cp=Math.cos(i); 
        var ox = R2 + R1*ct, 
            oy = R1*st;

        var x = ox*(cB*cp + sA*sB*sp) - oy*cA*sB; 
        var y = ox*(sB*cp - sA*cB*sp) + oy*cA*cB;
        var ooz = 1/(K2 + cA*ox*sp + sA*oy); 
        var xp=(150+K1*ooz*x);
        var yp=(120-K1*ooz*y); 
        var L=0.7*(cp*ct*sB - cA*ct*sp - sA*st + cB*(cA*st - ct*sA*sp));
        if(L > 0) {
          ctx.fillStyle = 'rgba(255,255,255,'+L+')';
          ctx.fillText('+', xp, yp);
        }
      }
    }
  }


function step(ts) {
  if (!start) {
    start = ts;
  }  
  var dx = ts - start;
  window.requestAnimationFrame(step);
  ctx.clearRect(0,0,300,300);
  canvasframe();
}

window.requestAnimationFrame(step);
