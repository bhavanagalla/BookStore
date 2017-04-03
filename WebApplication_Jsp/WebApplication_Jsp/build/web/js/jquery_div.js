/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


 $(document).ready(function(){

  // Hide div 2 by default
  $('#Update1').hide();

  $('#Updatel').click(function(){ 
      $('#insert').hide();
      $('#Update1').show();
  });

  $('#insertl').click(function(){ 
      $('#Update1').hide();
      $('#insert').show();
  }); 
});