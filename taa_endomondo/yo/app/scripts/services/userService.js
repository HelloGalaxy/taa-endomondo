angular.module('yoApp')
.service('userService', function ($resource, $http) {
   
     this.getUsers = function () 
    {  
        return $resource('./rest/user/allusers').query();
    };
    this.addFriend = function (mynickname, friendmail){
	       
	   // alert("inside user Service call to server "+mynickname+"  wants to add a friend :"+friendmail);
	    return $resource("./rest/user/addFriendByEmail/"+mynickname+"/"+friendmail).save({});
	}
	
	this.getFriends = function(usernickname){
		   // alert("user service retrieving friends : for"+usernickname);
		   return $resource("./rest/user/getMyFriends/"+usernickname).query();
     }
    this.getChallenges = function (usernickname){
		  return $resource("./rest/user/getMyChallenges/"+usernickname).query();
	}
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
			return null;
	};
	
	this.registerNewUser = function (newUser){
	  
	      return $resource("./rest/user").save(newUser);      	
	};
	
	this.senMail = function (newMessage, fromEmail, toEmail){
		/* 
		 * This first method of sending caused me a problem at the begenning of 
		 * cean ..;
		 * */
	/*	$http({
			url: './rest/msg/send/'+fromEmail+'/'+toEmail',
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			data: {"content": newMessage}
		}).success(function (data, status, headers, config) {
            alert( "Message sent successfuly: if data = true the it was really sent"); // assign  $scope.persons here as promise is resolved here 
			}).error(function (data, status, headers, config) {
				alert("error!");
			});*/
     	
     	//  - second method of sending 
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
