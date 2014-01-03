angular.module('yoApp')
.service('planService', function ($resource) {
    
    var plans=$resource('./rest/plan/allPlans').query();
    
    this.getPlans = function () {
        return plans;
    };
    
    this.getRoute = function () {
		var rt = [{"attitude":591.0,"longitude":2.3488 ,"latitude":48.85341}];
        return rt;
    };
    this.registerPlan = function (newPlan){
	      alert("Post plan Donne"); 
	        //return $resource("./rest/gps").save({ "longitude":90 , "latitude":90 , "attitude":90 });
	      $resource("./rest/plan").save(newPlan);
	      return "OK";  	
	}; 
	this.joinPlan = function(nickname, planid){
		
	    return $resource("./rest/plan/"+planid+"/addUserByNickname/"+nickname).save({});   
	}
     this.registerRoute = function (newRoute){
	      alert("Post plan Donne"); 
	        //return $resource("./rest/gps").save({ "longitude":90 , "latitude":90 , "attitude":90 });
	      $resource("./rest/route").save(newRoute);
	      return "OK";  	
	};
	this.registerCoord = function (newCoord){
	      alert("Post plan Donne"); 
	       return $resource("./rest/gps").save({ "longitude":90 , "latitude":90 , "attitude":90 });
	      $resource("./rest/gps").save(newCoord);
	      return "OK";  	
	};
});
