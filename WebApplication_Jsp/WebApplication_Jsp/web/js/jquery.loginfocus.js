
jQuery(document).ready(function() {

    jQuery("#login-form").submit(function () {

        if(jQuery('#LoginForm_username').val()=='Login ID')
        {
            alert('Login ID should not be blank');
            jQuery('#LoginForm_username').focus();
            return false;
        }
        else if(jQuery('#LoginForm_password').val()=='password')
        {
            alert('Password should not be blank');
            jQuery('#LoginForm_password').focus();
            return false;
        }
        return true;
    });




	
    jQuery("#LoginForm_password").focus(function() {
        //var uval=jQuery('#LoginForm_username').val();
        var pval=jQuery('#LoginForm_password').val();
	
        //if(uval=='Login ID')
        //jQuery('#LoginForm_username').val('');
	
        if(pval=='password')
            jQuery('#LoginForm_password').val('');
    });
	
    jQuery("#LoginForm_password").blur(function() {
        var uval=jQuery('#LoginForm_username').val();
        var pval=jQuery('#LoginForm_password').val();
	
        if(uval=='')
            jQuery('#LoginForm_username').val('Login ID');
	
        if(pval=='')
            jQuery('#LoginForm_password').val('password');
    });
	
	
    jQuery("#LoginForm_username").focus(function() {
        var uval=jQuery('#LoginForm_username').val();
        //var pval=jQuery('#LoginForm_password').val();
	
        if(uval=='Login ID')
            jQuery('#LoginForm_username').val('');
	
    //if(pval=='password')
    //jQuery('#LoginForm_password').val('');
    });
	
    jQuery("#LoginForm_username").blur(function() {
        var uval=jQuery('#LoginForm_username').val();
        var pval=jQuery('#LoginForm_password').val();
	
        if(uval=='')
            jQuery('#LoginForm_username').val('Login ID');
	
        if(pval=='')
            jQuery('#LoginForm_password').val('password');
    });
	
});

