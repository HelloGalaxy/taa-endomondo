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
  .controller('UserCtrl', function ($scope, userService, $window, $http,  $location, $rootScope, $cookieStore) {
      $scope.users = userService.getUsers();
     
      $scope.visitorsession=[]; 
      
      
      
      $scope.loginRRRSaved = function(){
		  
		    if($cookieStore.get('loggedin')!='true'){
		        alert('new cookie created');$cookieStore.put('loggedin', 'true'); $location.path( "/logged" );
		    }else{
				  alert('cokkie exist'); $cookieStore.remove('loggedin');
				 }    
	  }
	  
	    $scope.loginRRR = function(){

		    if($cookieStore.get('loggedin')!='true'){
		    
		         $http.get("./rest/user/"+$scope.visitor.nickname+"/"+$scope.visitor.mail).success(function(data, status, headers, config) {  //./rest/user/1?callback=JSON_CALLBACK
				        if(status == 200) { 
						 $cookieStore.put('loggedin', 'true'); 
		                 $cookieStore.put('nickname', data.nickname);  
				         //alert(data.nickname);
				         alert("Hello , "+$cookieStore.get('nickname'));
				         $location.path( "/logged" );
				         //$window.location='/#/logged';
				         
				        } else
				          alert(" Invalide user nickname or email !");
			    }).error(function(data, status, headers, config) {
			             alert(" error !");
			    });	
			    
		    }else{
				  //alert('cokkie exist'); 
				  $cookieStore.remove('loggedin');
				  $cookieStore.remove('nickname');
				  $location.path( "/logged" );
				 } 
	  }
	  
	  // sing out the user
	  $scope.signOut  = function(){
		    alert('sign out clicke'); 
		    $cookieStore.remove('loggedin');
		    $cookieStore.remove('nickname');
		    $location.path( "/" );
	   }
	  
	 /* $scope.$watch(function() { return $location.path(); }, function(newValue, oldValue){  
      if ($scope.loggedIn == false && newValue != '/login'){  
             $location.path('/login');  
       }  
      }); */
	      
      $scope.login = function(){
			
			   $http.get("./rest/user/1").success(function(data, status, headers, config) {  //./rest/user/1?callback=JSON_CALLBACK
				        if(status == 200) { $scope.visitorsession = $scope.visitor.nickname ; 
				        // alert(data.lastname);
				         alert($scope.visitorsession);
				        ;$window.location='/#/logged';
				         
				        } else
				          alert(" Invalide user nickname or email !");
			    }).error(function(data, status, headers, config) {
			            $scope.visitorsession = null; alert(" error !");
			    });	
			    //$window.location='/#/logged';		
				//$scope.visitorsession = userService.getUser($scope.visitor.mail, $scope.visitor.nickname ); 
				// $scope.visitorsession = userService.getUser('a@yahoo.fr', 'Jeany' ); 
				// alert("inside mainJs : vis session :"+ $scope.visitorsession);  
				/* if($scope.visitorsession==null){  
					   alert("Invalide nick name or mail  ?");
					}else{
						   alert(" valide nick name or mail  ?");
						   $window.location='/#/logged';
		    	 }*/
		  
			     alert("end of login in login(): "+$scope.visitorsession);
       }
    
       $scope.registeruser = function(){
		   
		     /*var newUser = { "height":$scope.newuser.height,"firstname":$scope.newuser.firstname,"lastname":$scope.newuser.lastname,
				              "nickname":$scope.newuser.nickname,"sex":$scope.newuser.sex,"birthday":$scope.newUser.birthday,
				              "weight":$scope.newuser.weight,"avatar":null,"facebook":$scope.newuser.facebook,"joindate":"0-0-0",
				              "friends":null,"plans":null,"email":$scope.newuser.email};*/
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
 });
angular.module('yoApp')
  .controller('PlanCtrl', function ($scope, planService) {
      $scope.plans = planService.getPlans();
      
      // $scope.getroute = function(){
		    $scope.route = planService.getRoute();
		//    alert("get route called");
	    //}
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
