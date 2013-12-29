//This handles retrieving data and is used by controllers. 3 options (server, factory, provider) with 
//each doing the same thing just structuring the functions/data differently.
angular.module('yoApp')
.service('userService', function ($resource) {
    
   
    this.senMail = function (newMessage){
		
	    // th idea is to create a  message and send it 
	    return $resource("./rest/msg").save(newMessage);
        
	};
	
	
});
