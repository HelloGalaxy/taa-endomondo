'use strict';
angular.module('yoApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
angular.module('yoApp')
.controller('UserCtrl', function($scope, userService, $window, $http,  $location, $cookieStore){
	 $scope.currentuser = $cookieStore.get('nickname');
	 $scope.users = userService.getUsers(); 
	   $scope.login= function(){
            alert('click on login');
		    if($cookieStore.get('loggedin')!='true'){
		    
		         $http.get("./rest/user/"+$scope.visitor.nickname+"/"+$scope.visitor.mail).success(function(data, status, headers, config) {  //./rest/user/1?callback=JSON_CALLBACK
				        if(status == 200) { 
						 $cookieStore.put('loggedin', 'true'); 
		                 $cookieStore.put('nickname', data.nickname);  
				        // alert("Hello , "+$cookieStore.get('nickname'));
				         $location.path( "/logged" );
				        } else
				          alert(" Invalide user nickname or email !");
			    }).error(function(data, status, headers, config) {
			             alert(" error !");
			    });	
			    
		    }else{
				    $location.path( "/logged" );
				 } 
	  }
	  
	  // sing out the user
	  $scope.signOut  = function(){
		    alert('sign out click'); 
		    $cookieStore.remove('loggedin');
		    $cookieStore.remove('nickname');
		    $location.path( "/" );
	   }
	     $scope.registeruser = function(){
		   
				var newUser = {"height":$scope.newuser.height, "firstname":$scope.newuser.firstname,"lastname":$scope.newuser.lastname,
				              "nickname":$scope.newuser.nickname,"sex":$scope.newuser.sex,"weight":$scope.newuser.weight,"avatar":null,"facebook":$scope.newuser.facebook,
				              "friends":null,"plans":null,"email":$scope.newuser.email, "birthday":$scope.newuser.birthday};
				alert("reggting user ok ! ");         
			    userService.registerNewUser(newUser);	 
		   } 
		   
		     
	    $scope.messgSend= function(){
		    var newMessage = { "content":$scope.messgText.content};
		    alert($cookieStore.get('nickname')+" , will send a message");
		    // send mail ===>  as parameters give the cookies stroe nickname + data from the form, in form no need for the id .... just mail/ nicknames
		    //alert("frtom"+$scope.messgfrom.email+", to "+$scope.messgto.email);
		    userService.senMail(newMessage,$scope.messgfrom.email,$scope.messgto.email);
		    
		}
		
		$scope.addFriend = function(friendmail){ // friendmail //user.email
			
			  alert(" add friend requested from : "+friendmail);
			  alert($cookieStore.get('nickname')+" , you have a new friend !");
			  userService.addFriend($cookieStore.get('nickname'), friendmail);
	    }	
	    
	    $scope.friends = userService.getFriends($cookieStore.get('nickname'));
	    $scope.challenges = userService.getChallenges($cookieStore.get('nickname'));  
		
	});
angular.module('yoApp')
  .controller('PlanCtrl', function ($scope, planService, $cookieStore) {
      $scope.plans = planService.getPlans();
      
      // $scope.getroute = function(){
		    $scope.route = planService.getRoute();
		//    alert("get route called");
	    //}
	    
	  $scope.joinChallenge = function (planid){  
		     alert($cookieStore.get('nickname')+" , you will be added to the plan !"+planid);
		     planService.joinPlan($cookieStore.get('nickname'), planid);
		     alert(" you are now a memeber of that plan");
	  }
	    
      $scope.registerPlan = function(){
		     var lisCoord=[];
		    
		     
		     var coordRef = {"longitude":50.0,"latitude":52.0,"attitude":53.0};
		     var coordRef2 = { "longitude":60.0,"latitude":60.0,"attitude":60.0};
		     lisCoord.push(coordRef); lisCoord.push(coordRef2);
		     var routeRef = {"coordGpsList":lisCoord};
		     planService.registerRoute(routeRef);
		     
		
		     //planService.registerCoord(coordRef);  planService.registerCoord(coordRef2);
		    
		     /*var listCoord=[]; listCoord.push(coordRef);
		     var routeRef= {"coordGpsList":listCoord};
		     //var retst= planService.registerRoute(routeRef);
		     var nplan={"title":$scope.newWOut.title,"note":$scope.newWOut.note,"startDate":$scope.newWOut.startdate,
		                "endDate":$scope.newWOut.enddate, "weather":null,"sportType":null,"route":routeRef};
		     var retst= planService.registerPlan(nplan); 
		     */
		     alert(retst);          
		     //alert("title : "+$scope.newWOut.title+" Note :"+$scope.newWOut.note+);
		  }
  });
