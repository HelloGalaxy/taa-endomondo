angular.module('yoApp')
.service('userService', function ($resource, $http, $location, $cookieStore) {
   
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
        /*    $http.get('./rest/user/1')
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
	  
	      //return $resource("./rest/user").save(newUser); 
	      return $resource("./rest/user").save(newUser, function (response){
			   if(response.id >= 0) {
				    alert("user registered successfully !");
				    $cookieStore.put('loggedin', 'true'); 
		            $cookieStore.put('nickname', response.nickname);
				    $location.path( "/logged" );
				   }else alert(" Please verify the email and nickname, they should be unique in the system ! ");
			});     	
	};
	
	this.senMail = function (newMessage, fromEmail, toEmail, $http){
		
		$resource('./rest/msg/send/'+fromEmail+'/'+toEmail).save(newMessage, function (response){
			   if(response.id >= 0) {
				     alert("message sent successfuly !");
				      $location.path( "/logged" );
				   }else alert("message not sent , please verify the emails ! ");
			});
		
	   /*$http({
			url: './rest/msg/send/'+fromEmail+'/'+toEmail,
			method: "POST",
			data: {"content": newMessage},
			headers: { 'Content-Type': 'application/json' }
		    }).success(function (data, status, headers, config) {
             //  if(status == 200) {  alert("Message sent succesfully");
				         
				//        } else
				  //        alert(" error ");
			}).error(function (data, status, headers, config) {
				//alert("error!");
			});
         */
	};
	
});
