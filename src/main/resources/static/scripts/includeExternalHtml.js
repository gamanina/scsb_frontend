var includeExternalHTML=function(){for(var e,t,n,i=document.getElementsByTagName("*"),l=0;l<i.length;l++)if(t=(e=i[l]).getAttribute("include-html"))return(n=new XMLHttpRequest).onreadystatechange=function(){4==this.readyState&&(200==this.status&&(e.innerHTML=this.responseText),404==this.status&&(e.innerHTML=""),e.removeAttribute("include-html"),null!=window.setMenuActive&&window.setMenuActive(),includeExternalHTML())},n.open("GET",t,!0),void n.send()};window.loadingCompleteCallback=function(){console.log("loading completed"),includeExternalHTML()};