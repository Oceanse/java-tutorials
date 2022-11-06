(function(){/* 
 
 Copyright The Closure Library Authors. 
 SPDX-License-Identifier: Apache-2.0 
*/ 
'use strict';var g=this||self;function ba(){} 
function ca(a){var b=typeof a;if("object"==b)if(a){if(a instanceof Array)return"array";if(a instanceof Object)return b;var c=Object.prototype.toString.call(a);if("[object Window]"==c)return"object";if("[object Array]"==c||"number"==typeof a.length&&"undefined"!=typeof a.splice&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("splice"))return"array";if("[object Function]"==c||"undefined"!=typeof a.call&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("call"))return"function"}else return"null"; 
else if("function"==b&&"undefined"==typeof a.call)return"object";return b}function da(a,b,c){return a.call.apply(a.bind,arguments)}function ea(a,b,c){if(!a)throw Error();if(2<arguments.length){var d=Array.prototype.slice.call(arguments,2);return function(){var e=Array.prototype.slice.call(arguments);Array.prototype.unshift.apply(e,d);return a.apply(b,e)}}return function(){return a.apply(b,arguments)}} 
function k(a,b,c){Function.prototype.bind&&-1!=Function.prototype.bind.toString().indexOf("native code")?k=da:k=ea;return k.apply(null,arguments)}var fa=Date.now||function(){return+new Date};function q(a,b){function c(){}c.prototype=b.prototype;a.B=b.prototype;a.prototype=new c;a.prototype.constructor=a};function r(a,b){Array.prototype.forEach.call(a,b,void 0)};function ha(a){let b=!1,c;return function(){b||(c=a(),b=!0);return c}};function u(a,b){this.b=a===v&&b||"";this.c=ia}u.prototype.f=!0;u.prototype.a=function(){return this.b.toString()};var ja=/^(?:(?:https?|mailto|ftp):|[^:/?#]*(?:[/?#]|$))/i;function ka(a){if(a instanceof u)return a;a="object"==typeof a&&a.f?a.a():String(a);ja.test(a)||(a="about:invalid#zClosurez");return new u(v,a)}var ia={},v={};function w(a){w[" "](a);return a}w[" "]=ba;function x(){}var la="function"==typeof Uint8Array;function z(a,b,c){a.a=null;b||(b=[]);a.j=void 0;a.f=-1;a.b=b;a:{if(b=a.b.length){--b;var d=a.b[b];if(!(null===d||"object"!=typeof d||Array.isArray(d)||la&&d instanceof Uint8Array)){a.g=b-a.f;a.c=d;break a}}a.g=Number.MAX_VALUE}a.i={};if(c)for(b=0;b<c.length;b++)if(d=c[b],d<a.g)d+=a.f,a.b[d]=a.b[d]||A;else{var e=a.g+a.f;a.b[e]||(a.c=a.b[e]={});a.c[d]=a.c[d]||A}}var A=[]; 
function C(a,b){if(b<a.g){b+=a.f;var c=a.b[b];return c===A?a.b[b]=[]:c}if(a.c)return c=a.c[b],c===A?a.c[b]=[]:c}function D(a,b,c){a=C(a,b);return null==a?c:a}function F(a,b){a=C(a,b);a=null==a?a:!!a;return null==a?!1:a}function ma(a){var b=G;a.a||(a.a={});if(!a.a[1]){var c=C(a,1);c&&(a.a[1]=new b(c))}return a.a[1]}function na(a){var b=oa;a.a||(a.a={});if(!a.a[1]){for(var c=C(a,1),d=[],e=0;e<c.length;e++)d[e]=new b(c[e]);a.a[1]=d}b=a.a[1];b==A&&(b=a.a[1]=[]);return b}x.prototype.toString=function(){return this.b.toString()};function G(a){z(this,a,pa)}q(G,x);function oa(a){z(this,a,null)}q(oa,x);var pa=[1];function qa(a){z(this,a,null)}q(qa,x);var ra={capture:!0},sa={passive:!0},ta=ha(function(){let a=!1;try{const b=Object.defineProperty({},"passive",{get:function(){a=!0}});g.addEventListener("test",null,b)}catch(b){}return a});function ua(a){return a?a.passive&&ta()?a:a.capture||!1:!1}function H(a,b,c,d){a.addEventListener&&a.addEventListener(b,c,ua(d))};var va=(a=null)=>a&&23==a.getAttribute("data-jc")?a:document.querySelector('[data-jc="23"]');var I=document,J=window;function wa(a,b,c){if(Array.isArray(b))for(var d=0;d<b.length;d++)wa(a,String(b[d]),c);else null!=b&&c.push(a+(""===b?"":"="+encodeURIComponent(String(b))))};function K(){var a=L;try{var b;if(b=!!a&&null!=a.location.href)a:{try{w(a.foo);b=!0;break a}catch(c){}b=!1}return b}catch(c){return!1}}var xa=a=>{"complete"===I.readyState||"interactive"===I.readyState?a():I.addEventListener("DOMContentLoaded",a)};var ya=/^((market|itms|intent|itms-appss):\/\/)/i;var za=()=>{var a=`${"http:"===J.location.protocol?"http:":"https:"}//${"pagead2.googlesyndication.com"}/pagead/gen_204`;return b=>{b={id:"unsafeurl",ctx:625,url:b};var c=[];for(d in b)wa(d,b[d],c);var d=c.join("&");if(d){b=a.indexOf("#");0>b&&(b=a.length);c=a.indexOf("?");if(0>c||c>b){c=b;var e=""}else e=a.substring(c+1,b);b=[a.substr(0,c),e,a.substr(b)];c=b[1];b[1]=d?c?c+"&"+d:d:c;d=b[0]+(b[1]?"?"+b[1]:"")+b[2]}else d=a;navigator.sendBeacon&&navigator.sendBeacon(d,"")}};var Aa=(a,b)=>{if(a)for(let c in a)Object.prototype.hasOwnProperty.call(a,c)&&b.call(void 0,a[c],c,a)},Ba=!!window.google_async_iframe_id;let L=Ba&&window.parent||window;var Ca=(a,b)=>{a&&Aa(b,(c,d)=>{a.style[d]=c})},Da=a=>{var b=I.body;const c=document.createDocumentFragment(),d=a.length;for(let e=0;e<d;++e)c.appendChild(a[e]);b.appendChild(c)};var Ea=(a=[])=>{g.google_logging_queue||(g.google_logging_queue=[]);g.google_logging_queue.push([12,a])};let M=null;class Fa{constructor(a,b,c,d=0,e){this.label=a;this.type=b;this.value=c;this.duration=d;this.uniqueId=Math.random();this.slotId=e}};const N=g.performance,Ga=!!(N&&N.mark&&N.measure&&N.clearMarks),O=ha(()=>{var a;if(a=Ga){var b;if(null===M){M="";try{a="";try{a=g.top.location.hash}catch(c){a=g.location.hash}a&&(M=(b=a.match(/\bdeid=([\d,]+)/))?b[1]:"")}catch(c){}}b=M;a=!!b.indexOf&&0<=b.indexOf("1337")}return a});function Ha(a){a&&N&&O()&&(N.clearMarks(`goog_${a.label}_${a.uniqueId}_start`),N.clearMarks(`goog_${a.label}_${a.uniqueId}_end`))} 
class Ia{constructor(a,b){this.a=[];this.c=b||g;let c=null;b&&(b.google_js_reporting_queue=b.google_js_reporting_queue||[],this.a=b.google_js_reporting_queue,c=b.google_measure_js_timing);this.b=O()||(null!=c?c:Math.random()<a)}start(a,b){if(!this.b)return null;var c=(c=g.performance)&&c.now?c.now():null;c||(c=(c=g.performance)&&c.now&&c.timing?Math.floor(c.now()+c.timing.navigationStart):fa());a=new Fa(a,b,c);b=`goog_${a.label}_${a.uniqueId}_start`;N&&O()&&N.mark(b);return a}};var T;if(Ba&&!K()){let a="."+I.domain;try{for(;2<a.split(".").length&&!K();)I.domain=a=a.substr(a.indexOf(".")+1),L=window.parent}catch(b){}K()||(L=window)}T=L;const U=new Ia(1,T);var Ja=()=>{T.google_measure_js_timing||(U.b=!1,U.a!=U.c.google_js_reporting_queue&&(O()&&r(U.a,Ha),U.a.length=0))};"number"!==typeof T.google_srt&&(T.google_srt=Math.random());"complete"==T.document.readyState?Ja():U.b&&H(T,"load",()=>{Ja()});var Ka=a=>{H(J,"message",b=>{let c;try{c=JSON.parse(b.data)}catch(d){return}!c||"ig"!==c.googMsgType||a(c,b)})};function V(){this.b=this.b;this.c=this.c}V.prototype.b=!1;function La(a){a.b||(a.b=!0,a.f())}V.prototype.f=function(){if(this.c)for(;this.c.length;)this.c.shift()()};function W(a,b,c){V.call(this);this.g=a;this.s=b||0;this.i=c;this.j=k(this.m,this)}q(W,V);W.prototype.a=0;W.prototype.f=function(){W.B.f.call(this);this.stop();delete this.g;delete this.i};W.prototype.start=function(a){this.stop();var b=this.j;a=void 0!==a?a:this.s;if("function"!=ca(b))if(b&&"function"==typeof b.handleEvent)b=k(b.handleEvent,b);else throw Error("Invalid listener argument");this.a=2147483647<Number(a)?-1:g.setTimeout(b,a||0)}; 
W.prototype.stop=function(){0!=this.a&&g.clearTimeout(this.a);this.a=0};W.prototype.m=function(){this.a=0;this.g&&this.g.call(this.i)};const Ma={display:"inline-block",position:"absolute"},Na={display:"none",width:"100%",height:"100%",top:"0",left:"0"},X=(a,b)=>{a&&(a.style.display=b?"inline-block":"none")};function Oa(a=""){const b={top:0,right:0,bottom:0,left:0};a&&(a=a.split(","),4==a.length&&a.reduce((c,d)=>c&&!isNaN(d),!0)&&([b.top,b.right,b.bottom,b.left]=a.map(c=>+c)));return b} 
function Pa(a,b,c=2147483647){const d=I.createElement("DIV");Ca(d,Object.assign(Ma,{"z-index":c},b));F(a.a,10)&&H(d,"click",ba);if(F(a.a,11)){a=b=I.createElement("A");c=za();var e;ya.test("#")?e=new u(v,"#"):e="#";"about:invalid#zClosurez"===(e instanceof u?e:ka(e)).a()&&c(String(e));e=e instanceof u?e:ka(e);a.href=e instanceof u&&e.constructor===u&&e.c===ia?e.b:"type_error:SafeUrl";b.appendChild(d);return b}return d} 
function Qa(a,b){switch(D(b.h,5,1)){case 2:J.AFMA_Communicator&&J.AFMA_Communicator.addEventListener&&J.AFMA_Communicator.addEventListener("onshow",()=>{Y(a,b)});break;case 10:H(J,"i-creative-view",()=>{Y(a,b)});break;case 4:H(I,"DOMContentLoaded",()=>{Y(a,b)});break;case 8:Ka(c=>{c.rr&&Y(a,b)});break;case 9:if(J.IntersectionObserver){const c=new IntersectionObserver(d=>{for(let e of d)if(0<e.intersectionRatio){Y(a,b);break}});c.observe(I.body);a.A.push(c)}break;case 11:J.AFMA_Communicator&&J.AFMA_Communicator.addEventListener&& 
J.AFMA_Communicator.addEventListener("onAdVisibilityChanged",()=>{Y(a,b)})}}function Ra(a,b){b=Oa(b);const c=D(a.a,9,0);a.f=[{width:"100%",height:b.top+c+"px",top:-c+"px",left:"0"},{width:b.right+c+"px",height:"100%",top:"0",right:-c+"px"},{width:"100%",height:b.bottom+c+"px",bottom:-c+"px",left:"0"},{width:b.left+c+"px",height:"100%",top:"0",left:-c+"px"}].map(d=>Pa(a,d,9019))} 
function Sa(a){var b=0;for(let d of a.w){const e=d.h,p=a.m[D(e,5,1)];d.l||void 0===p||(b=Math.max(b,p+D(e,2,0)))}a.g&&La(a.g);b-=Date.now();const c=a.b;0<b?(X(c,!0),a.g=new W(()=>{X(c,!1)},b),a.g.start()):X(c,!1)}function Y(a,b){if(!b.l){var c=D(b.h,5,1);a.m[c]=Date.now();F(b.h,9)&&(a.w.push(b),Sa(a))}} 
class Ta{constructor(){this.f=[];this.g=this.b=null;this.w=[];this.a=null;this.s=[];this.c=[];this.j=[];this.m={};this.A=[];this.i=null}init(a){Ea([a]);this.a=new qa(a);a=ma(this.a);r(na(a),e=>{this.j.push({u:0,l:!1,v:0,h:e,o:-1})});try{var b=I.querySelectorAll("*[data-ifc]")}catch(e){b=[]}this.c=b;let c=!1;b=this.c.length;for(let e=0;e<b;++e)a=new G(JSON.parse(this.c[e].getAttribute("data-ifc")||"[]")),r(na(a),p=>{this.j.push({u:0,l:!1,v:0,h:p,o:e});1===D(p,4,1)&&(c=!0)});b=!1;for(var d of this.j)a= 
d.h,0<D(a,2,0)&&0<D(a,5,1)?(!this.b&&F(a,9)&&(this.b=Pa(this,Na)),Qa(this,d)):D(a,1,"")&&F(a,9)&&Ra(this,D(a,1,"")),D(a,1,"")&&(b=!0);d=[];this.b&&d.push(this.b);!c&&d.push(...this.f);I.body&&Da(d);F(this.a,13)&&xa(()=>{const e=I.body.querySelectorAll(".amp-fcp, .amp-bcp");for(let f=0;f<e.length;++f){var p=(p=e[f])?J.getComputedStyle(p).getPropertyValue("position"):void 0;"absolute"===p&&X(e[f],!1)}});H(I,"click",e=>{var p=-1,f=[];for(let y of this.j){var h=y.o,m=-1!==h;if(!(D(y.h,3,0)<=p||y.l||m&& 
!1===f[h])){var n=!m||f[h]||this.c[h].contains(e.target);m&&n&&(f[h]=!0);if(h=n)if(h=e,n=y,m=n.h,0<D(m,2,0)&&0<D(m,5,1))h=this.m[D(m,5,1)],h=void 0!==h&&Date.now()<h+D(m,2,0);else if(D(m,1,"")){{m=(0<=n.o?this.c[n.o]:I.body).getBoundingClientRect();var E=parseFloat;var B=(B=I.body)?J.getComputedStyle(B).getPropertyValue("zoom"):void 0;E=E(B||1);const [Ua,Va]=[h.clientX,h.clientY],[P,Q,Z,aa]=[Ua/E-m.left,Va/E-m.top,m.width,m.height];if(!(0<Z&&0<aa)||isNaN(P)||isNaN(Q)||0>P||0>Q)h=!1;else{n=Oa(D(n.h, 
1,""));B=!(P>=n.left&&Z-P>n.right&&Q>=n.top&&aa-Q>n.bottom);if(this.i&&F(this.a,12)&&500>h.timeStamp-this.i.timeStamp){h=this.i.changedTouches[0];const [R,S]=[h.clientX/E-m.left,h.clientY/E-m.top];!isNaN(R)&&!isNaN(S)&&0<=R&&0<=S&&(B=B||!(R>=n.left&&Z-R>n.right&&S>=n.top&&aa-S>n.bottom))}h=B}}}else h=!0;if(h){var l=y;p=D(y.h,3,0)}}}if(l)switch(p=l.h,D(p,4,1)){case 2:case 3:e.preventDefault?e.preventDefault():e.returnValue=!1;f=Date.now();500<f-l.v&&(l.v=f,++l.u);f=l.h;if(D(f,8,0)&&l.u>=D(f,8,0))if(l.l= 
!0,this.b&&0<D(f,2,0))Sa(this);else if(0<this.f.length&&D(f,1,""))for(var t of this.f)X(t,!1);if(!(.01<Math.random())){t=(t=va(document.currentScript))&&t.getAttribute("data-jc-version")||"unknown";t=`https://${"pagead2.googleadservices.com"}/pagead/gen_204?id=jca&jc=${23}&version=${t}&sample=${.01}`;l=window;if(f=l.navigator)f=l.navigator.userAgent,f=/Chrome/.test(f)&&!/Edge/.test(f)?!0:!1;f&&l.navigator.sendBeacon?l.navigator.sendBeacon(t):(l.google_image_requests||(l.google_image_requests=[]), 
f=l.document.createElement("img"),f.src=t,l.google_image_requests.push(f))}t=p.b;for(let y of this.s)y(e,t)}},ra);b&&F(this.a,12)&&H(I,"touchend",e=>{this.i=e},sa)}registerCallback(a){this.s.push(a)}};window.googqscp=new Ta;}).call(this);
