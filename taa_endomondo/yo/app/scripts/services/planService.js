angular.module('yoApp')
.service('planService', function ($resource, $cookieStore, $location) {
    
    var plans=$resource('./rest/plan/allPlans').query();
    
    this.getPlans = function () {
        return plans;
    };
    
    this.getRoute = function () {
		  var rt = [{"attitude":591.0,"longitude":2.3488 ,"latitude":48.85341}];
          return rt;
    };
    this.registerPlan = function (newPlan){
          //alert("inside register plan");
	      return $resource("./rest/plan/"+$cookieStore.get('nickname')+"/createplan").save(newPlan,function (response){
			   if(response.id >= 0) {
				    alert("plan created successfully !");
				    $location.path( "/logged" );
				   }else alert(" Plan  could not be created ! ");
			});     	   	
	}; 
	this.joinPlan = function(nickname, planid){
		
	    return $resource("./rest/plan/"+planid+"/addUserByNickname/"+nickname).save({});   
	}
    
    this.registerRoute = function (newRoute){
	     return $resource("./rest/route").save(newRoute);  	
	};
	this.registerCoord = function (newCoord){
	      alert("Post plan Donne"); 
	       return $resource("./rest/gps").save({ "longitude":90 , "latitude":90 , "attitude":90 });
	      $resource("./rest/gps").save(newCoord);
	      return "OK";  	
	};
});
