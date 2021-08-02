(this["webpackJsonpbioatles-frontend"]=this["webpackJsonpbioatles-frontend"]||[]).push([[0],{117:function(e,o,s){},121:function(e,o,s){},122:function(e,o,s){"use strict";s.r(o);var i=s(0),n=s(16),f=s.n(n),t=(s(95),s(25)),r=s(75),p=s.n(r),u=s(127),l=s(126),c=s(128),b=s(130),g=s(125),a=s(76),y=s(48),d=s(9),_=function(e){e.selectedSpecies;var o=e.displayedTiles,s=e.selectedSpeciesChangeHandler,n=e.displayedSpeciesChangeHandler,f=Object(i.useState)(null),r=Object(t.a)(f,2),p=r[0],u=r[1],l=Object(i.useRef)(null);if(Object(i.useEffect)((function(){Object(a.loadModules)(["esri/WebMap","esri/views/MapView","esri/layers/GraphicsLayer","esri/widgets/Search"]).then((function(e){var o=Object(t.a)(e,4),i=o[0],f=o[1],r=o[2],p=o[3],c=new i({portalItem:{id:"884dd1c129b84c72a1db164c4fb85095",portal:{url:"https://portalideib.caib.es/portal"}}});c.infoWindow=null;var b=new r({id:"bioatles-graphics",graphics:null});c.add(b),u(c);var g=new f({container:l.current,map:c,zoom:2}),a=new p({view:g});g.ui.add(a,"top-right"),g.on("click",(function(e){c.findLayerById("bioatles-graphics").removeAll();var o=c.findLayerById("GOIB_DistEspecies_IB_9660");g.hitTest(e).then((function(e){e.screenPoint&&function(e,o){var i=g.toMap(e);o.queryFeatures({geometry:i,spatialRelationship:"intersects",returnGeometry:!1,outFields:["*"]}).then((function(e){var o={};o.geometry=y[e.features[0].attributes.Q_CODI].polygon,o.symbol={type:"simple-fill",color:"blue"},c.findLayerById("bioatles-graphics").add(o),s(null),n(y[e.features[0].attributes.Q_CODI].species)})).catch((function(e){return console.log("Query error",e)}))}(e.screenPoint,o.allSublayers.items[1])}))}))}))}),[]),p){var c=o?o.map((function(e){return{geometry:e in y?y[e].polygon:null,symbol:{type:"simple-fill",color:"#039962"}}})):null;c&&(p.findLayerById("bioatles-graphics").removeAll(),c.map((function(e){return p.findLayerById("bioatles-graphics").add(e)})))}return Object(d.jsx)("div",{id:"mapDiv",ref:l})},v=s(129),j=s(63),h=s.n(j),O=function(e){var o=e.selectedSpeciesChangeHandler,s=e.displayedTilesChangeHandler,n=Object(i.useState)(null),f=Object(t.a)(n,2),r=f[0],p=f[1],u=Object(i.useState)(null),l=Object(t.a)(u,2),c=l[0],b=l[1],g=function(e){return r===e?"#039962":"inherit"};Object(i.useEffect)((function(){return h()({method:"get",baseURL:"http://127.0.0.1:8080/",url:"serproes/api/services/especi/list",headers:{Authorization:" Bearer "+window.sessionStorage.getItem("token")}}).then((function(e){b(e.data.slice(0,30))})).catch((function(e){console.log("REST API error: ",e)})),function(){return null}}),[]);return Object(d.jsx)("div",{children:c&&c.map((function(e){return Object(d.jsxs)("div",{style:{margin:5,color:g(e.espCodi)},children:[e.espTaxon,Object(d.jsx)(v.a,{style:{margin:5},onClick:function(){var i;i=e.espCodi,h()({method:"get",baseURL:"http://127.0.0.1:8080/",url:"serproes/api/services/regist/findRegistByEspeci/".concat(i),headers:{Authorization:" Bearer "+window.sessionStorage.getItem("token")}}).then((function(e){s(e.data.map((function(e){return e.regCodcua.cuaCodq5}))),console.log("Response: ",e.data.map((function(e){return e.regCodcua.cuaCodq5})))})).catch((function(e){console.log("REST API error: ",e)})),p(e.espCodi),o(e.espCodi)}})]},e.espTaxon)}))})},m=s(77),S=(s(117),g.a.Title),x=function(e){var o=e.logoutHandler,s=e.username,n=Object(i.useState)(null),f=Object(t.a)(n,2),r=f[0],p=f[1],u=Object(i.useState)(null),l=Object(t.a)(u,2),g=l[0],a=l[1],y=Object(i.useState)(Object.keys(m)),v=Object(t.a)(y,2),j=(v[0],v[1]),h=function(e){p(e)};return Object(d.jsxs)(d.Fragment,{children:[Object(d.jsxs)(c.a,{className:"header",children:[Object(d.jsx)(b.a,{span:20,children:Object(d.jsx)(S,{className:"header-title",children:"Bioatles"})}),Object(d.jsx)(b.a,{span:2,style:{alignSelf:"center"},children:Object(d.jsx)("div",{children:Object(d.jsxs)("b",{children:["Benvingut ",Object(d.jsx)("span",{style:{color:"#039962",fontSize:20},children:s})," "]})})}),Object(d.jsx)(b.a,{span:2,style:{alignSelf:"center"},children:Object(d.jsx)("button",{className:"w3-btn w3-red",style:{borderRadius:8,color:"white",backgroundColor:"#A63A04",border:"none",fontSize:20},onClick:function(){return o()},children:"Logout"})})]}),Object(d.jsxs)(c.a,{children:[Object(d.jsxs)(b.a,{span:6,className:"left-col",children:[Object(d.jsx)(S,{level:3,className:"left-col-title",children:"Esp\xe8cies"}),Object(d.jsx)(O,{selectedSpeciesChangeHandler:h,displayedTilesChangeHandler:function(e){a(e)}})]}),Object(d.jsx)(b.a,{span:18,children:Object(d.jsx)(_,{selectedSpecies:r,displayedTiles:g,selectedSpeciesChangeHandler:h,displayedSpeciesChangeHandler:function(e){j(e)}})})]})]})},C=(s(121),function(){var e=Object(i.useState)(null),o=Object(t.a)(e,2),s=o[0],n=o[1],f=Object(i.useState)(null),r=Object(t.a)(f,2),c=r[0],b=r[1];Object(i.useEffect)((function(){var e=p()("/serproes-front/keycloak.json");return e.init({onLoad:"login-required",checkLoginIframe:!1}).then((function(o){n(e),e.realmAccess.roles.includes("SER_ADMIN")&&(b(o),console.log("username: "+e.realmAccess.roles),window.sessionStorage.setItem("token",e.token))})).catch((function(e){alert("Error initializing KEYCLOAK "+e)})),function(){return null}}),[]);var g=function(){s.logout()};return Object(d.jsx)(d.Fragment,{children:c&&s?Object(d.jsx)(x,{logoutHandler:g,username:s.idTokenParsed.preferred_username}):Object(d.jsx)(u.a,{status:"warning",title:"No s'ha pogut autenticar. Vost\xe9 no t\xe9 privilegis per  accedir a l'admin de BIOATLES.",extra:Object(d.jsx)(l.a,{type:"primary",onClick:g,children:"Tornar a formulari d'autenticaci\xf3"})})})}),w=function(e){e&&e instanceof Function&&s.e(3).then(s.bind(null,131)).then((function(o){var s=o.getCLS,i=o.getFID,n=o.getFCP,f=o.getLCP,t=o.getTTFB;s(e),i(e),n(e),f(e),t(e)}))};f.a.render(Object(d.jsx)(C,{}),document.getElementById("root")),w()},48:function(e){e.exports=JSON.parse('{"794":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.438,39.1541],[1.4954,39.1549],[1.4965,39.11],[1.4391,39.109]]}},"803":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.4953,39.1541],[1.5527,39.1549],[1.5538,39.11],[1.4964,39.109]]}},"812":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3234,39.1068],[1.3808,39.1076],[1.3819,39.0627],[1.3245,39.0617]]}},"813":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2671,39.0605],[1.3245,39.0614],[1.3256,39.0164],[1.2682,39.0154]]}},"814":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3244,39.0615],[1.3818,39.0624],[1.3829,39.0174],[1.3255,39.0164]]}},"821":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3807,39.1078],[1.4381,39.1086],[1.4392,39.0637],[1.3818,39.0627]]}},"822":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.438,39.1078],[1.4954,39.1086],[1.4965,39.0637],[1.4391,39.0627]]}},"823":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3817,39.0625],[1.4391,39.0634],[1.4402,39.0184],[1.3828,39.0174]]}},"824":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.439,39.0635],[1.4964,39.0644],[1.4975,39.0194],[1.4401,39.0184]]}},"831":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.4953,39.1088],[1.5527,39.1096],[1.5538,39.0647],[1.4964,39.0637]]}},"832":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5536,39.1098],[1.611,39.1106],[1.6121,39.0657],[1.5547,39.0647]]}},"833":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.4963,39.0635],[1.5537,39.0644],[1.5548,39.0194],[1.4974,39.0184]]}},"834":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5536,39.0645],[1.611,39.0654],[1.6121,39.0204],[1.5547,39.0194]]}},"843":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.6109,39.0655],[1.6683,39.0664],[1.6694,39.0214],[1.612,39.0214]]}},"851":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.1525,39.0142],[1.2099,39.0151],[1.211,38.9701],[1.1536,38.9691]]}},"852":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2098,39.0152],[1.2672,39.0161],[1.2683,38.9711],[1.2109,38.9701]]}},"853":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.1535,38.9699],[1.2101,38.9708],[1.211,38.9258],[1.1536,38.9248]]}},"854":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2118,38.9709],[1.2694,38.9718],[1.2703,38.9268],[1.2129,38.9258]]}},"861":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2671,39.0152],[1.3245,39.0161],[1.3256,38.9711],[1.2682,38.9701]]}},"862":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3244,39.0172],[1.3818,39.0181],[1.3829,38.9731],[1.3255,38.9721]]}},"863":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2691,38.9719],[1.3267,38.9728],[1.3276,38.9278],[1.2702,38.9268]]}},"864":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3274,38.9719],[1.385,38.9728],[1.3859,38.9278],[1.3285,38.9268]]}},"871":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3827,39.0172],[1.4401,39.0181],[1.4412,38.9731],[1.3838,38.9721]]}},"872":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.44,39.0182],[1.4974,39.0191],[1.4985,38.9741],[1.4411,38.9731]]}},"873":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3847,38.9719],[1.4423,38.9728],[1.4432,38.9278],[1.3858,38.9268]]}},"874":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.442,38.9739],[1.4996,38.9748],[1.5005,38.9298],[1.4431,38.9288]]}},"881":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.4983,39.0192],[1.5557,39.0201],[1.5568,38.9751],[1.4994,38.9741]]}},"882":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5556,39.0192],[1.613,39.0201],[1.6151,38.9751],[1.5567,38.9741]]}},"883":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.4993,38.9749],[1.5569,38.9758],[1.5578,38.9308],[1.5004,38.9298]]}},"892":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2128,38.9256],[1.2704,38.9265],[1.2713,38.8815],[1.2139,38.8805]]}},"893":{"species":["alytes_muletensis","buffo_buffotes"],"polygon":{"type":"polygon","rings":[[1.1555,38.8803],[1.2131,38.8812],[1.214,38.8362],[1.1566,38.8352]]}},"894":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2128,38.8803],[1.2704,38.8812],[1.2713,38.8362],[1.2139,38.8352]]}},"901":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2701,38.9266],[1.3277,38.9275],[1.3286,38.8825],[1.2712,38.8815]]}},"902":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3274,38.9266],[1.385,38.9275],[1.3859,38.8825],[1.3285,38.8815]]}},"903":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.2701,38.8803],[1.3277,38.8812],[1.3286,38.8362],[1.2712,38.8352]]}},"904":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3274,38.8803],[1.385,38.8812],[1.3859,38.8362],[1.3285,38.8352]]}},"911":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3847,38.9266],[1.4423,38.9275],[1.4432,38.8825],[1.3858,38.8815]]}},"912":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.442,38.9266],[1.4996,38.9275],[1.5005,38.8825],[1.4431,38.8815]]}},"913":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3847,38.8813],[1.4423,38.8822],[1.4432,38.8372],[1.3858,38.8362]]}},"922":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3294,38.8373],[1.3861,38.8379],[1.3879,38.7929],[1.3305,38.7919]]}},"931":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3867,38.8373],[1.4434,38.8379],[1.4452,38.7929],[1.3878,38.7919]]}},"932":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.444,38.8383],[1.5007,38.8389],[1.5025,38.7939],[1.4451,38.7929]]}},"933":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3877,38.793],[1.4444,38.7936],[1.4462,38.7486],[1.3888,38.7476]]}},"934":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.445,38.793],[1.5017,38.7936],[1.5035,38.7486],[1.4461,38.7476]]}},"942":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3324,38.7467],[1.3881,38.7473],[1.3899,38.7023],[1.3325,38.7013]]}},"944":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3324,38.7014],[1.3891,38.702],[1.3909,38.657],[1.3335,38.656]]}},"951":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3887,38.7477],[1.4454,38.7483],[1.4472,38.7033],[1.3898,38.7023]]}},"952":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.446,38.7487],[1.5027,38.7493],[1.5045,38.7043],[1.4471,38.7033]]}},"953":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3907,38.7024],[1.4474,38.703],[1.4492,38.658],[1.3918,38.657]]}},"954":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.448,38.7034],[1.5047,38.704],[1.5065,38.659],[1.4491,38.658]]}},"963":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5053,38.7044],[1.562,38.705],[1.5638,38.66],[1.5064,38.659]]}},"964":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5626,38.7054],[1.6193,38.706],[1.6211,38.661],[1.5637,38.66]]}},"972":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3334,38.6571],[1.3901,38.6577],[1.3919,38.6127],[1.3345,38.6117]]}},"981":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.3907,38.6581],[1.4474,38.6587],[1.4492,38.6137],[1.3918,38.6127]]}},"991":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5053,38.6581],[1.562,38.6587],[1.5638,38.6137],[1.5064,38.6127]]}},"992":{"species":["buffo_buffotes","buffo_viridis"],"polygon":{"type":"polygon","rings":[[1.5626,38.6581],[1.6193,38.6587],[1.6211,38.6137],[1.5637,38.6127]]}}}')},77:function(e){e.exports=JSON.parse('{"alytes_muletensis":["893"],"buffo_buffotes":["893","894","874","863"],"buffo_viridis":["894"]}')},95:function(e,o,s){}},[[122,1,2]]]);
//# sourceMappingURL=main.21a5b035.chunk.js.map