//This handles retrieving data and is used by controllers. 3 options (server, factory, provider) with 
//each doing the same thing just structuring the functions/data differently.
angular.module('yoApp')
.service('userService', function ($resource,$http) {
    
    var visitorsession=null; 
    
    this.getUsers = function () {
        return $resource('./rest/user/allusers').query();
    };
    
    this.getUser = function ( mail, nickname){
      /*  $http.get('./rest/user/1')
		 .then(
			  //success
			  function( data , status){ alert(angular.fromJson(data));},
			  //error
			  function( error ){alert("TT");}
		   )*/
		  /*    $http.get("./rest/user/2?callback=JSON_CALLBACK").success(function(data, status, headers, config) {
				if(status == 200) { visitorsession = nickname }; alert(" succes : "+visitorsession); 
			    }).error(function(data, status, headers, config) {
			            visitorsession = null;  alert(" fail : "+ visitorsession); 
			    });
			return(visitorsession);*/
	};
	
	this.registerNewUser = function (newUser){
	      alert("Post Donne"); 
	        //return $resource("./rest/gps").save({ "longitude":90 , "latitude":90 , "attitude":90 });
	      return $resource("./rest/user").save(newUser);
	        	
	};
	
	this.senMail = function (newMessage, fromEmail, toEmail){
		$http({
			url: './rest/msg/send/'+fromEmail+'/'+toEmail',
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			data: {"content": newMessage}
		}).success(function (data, status, headers, config) {
            alert( "Message sent successfuly: if data = true the it was really sent"); // assign  $scope.persons here as promise is resolved here 
			}).error(function (data, status, headers, config) {
				alert("error!");
			});
     	
		// $resource("./rest/msg/send/"+fromEmail+"/"+toEmail).save(newMessage);
	    // th idea is to create a  message and send it 
	     /*$http("./rest/msg/send/"+fromEmail+"/"+toEmail).post(newMessage).success(function(data, status, headers, config) {
				        if(status == 200) {  alert("OK");
				         
				        } else
				          alert(" not 200");
			    }).error(function(data, status, headers, config) {
			             alert(" error !");
			    });	
        */
	};
});
