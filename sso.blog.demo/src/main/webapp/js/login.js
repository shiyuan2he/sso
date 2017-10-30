var login = {
    requestUrl : 'http://localhost:8080/sso/login',
    getData : function(json){
        console.log(json) ;
    },
    getJsonp: function(serviceUrl,callbackFunc){
        $.ajax({
            type:"get",
            dataType : "jsonp",
            jsonp : "callback",
            jsonpCallback : callbackFunc,
            url:serviceUrl,
            dataType:'jsonp',
            success:function(data){
                console.log(data) ;
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    },
    getJson : function(serviceUrl){
        $.ajax({
            type : "get",
            dataType : "json",
            jsonp : "callback",
            url : serviceUrl,
            success:function(data){
                console.log(data) ;
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    },
    doLogin : function(){
        var url = login.requestUrl + "?username="+$('#username').val()+"&password="+$('#password').val()
        login.getJsonp(url,"login.getData") ;
    }
}
