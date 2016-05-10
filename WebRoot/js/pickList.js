(function ($) {

   $.fn.pickList = function (options) {

      var opts = $.extend({}, $.fn.pickList.defaults, options);

      this.fill = function () {
         var option = '';

         $.each(opts.data, function (key, val) {
            option += '<option  value=' + val.id + '>' + val.orgName + '</option>';
         });
         this.find('#pickData').append(option);
      };
   this.controll = function () {
         var pickThis = this;

  
      };

      this.init = function () {
         var pickListHtml =
                 "<div class='row'>" +
                 "  <div class='col-sm-5'>" +
                 "	 <select class='form-control pickListSelect' id='pickData' multiple></select>" +
                 " </div>" +
                 " <div class='col-sm-2 pickListButtons'>" +
                 "      <button id='pAddAll' onclick='addAll(this)' class='btn btn-primary btn-sm'>" + opts.addAll + "</button><br/>" +
                 "	<button id='pAdd' onclick=\"add(this)\" class='btn btn-primary btn-sm'>" + opts.add + "</button><br/>" +
                 "	<button id='pRemove' onclick='removes(this)' class='btn btn-primary btn-sm'>" + opts.remove + "</button><br/>" +
                 "	<button id='pRemoveAll'  onclick='removeAlls(this)' class='btn btn-primary btn-sm'>" + opts.removeAll + "</button><br/>" +
              /*   "<button id=\"getSelected\" onclick='get(this)' class=\"btn btn-primary\">Get Selected</button>"+*/
                 " </div>" +
                 " <div class='col-sm-5' name='sele'>" +
                 "    <select class='form-control pickListSelect' id='pickListResult' multiple></select>" +
                 " </div>" +
                 "</div>";

         this.append(pickListHtml);

         this.fill();
      };

      this.init();
      return this;
   };

   $.fn.pickList.defaults = {
      add: '选择部门',
      addAll: '选择所有',
      remove: '选择移除',
      removeAll: '移除所有'
   };

}(jQuery));
function get(obj){
	var objResult = [];
	var p=$(obj).parent().next().find("#pickListResult option").each(function () {
		objResult.push(this.id);
    });
	alert(objResult);
}

function removes(obj){
    var p=$(obj).parent().next().find("#pickListResult option:selected");
    var h=$(obj).parent().prev().children("#pickData");
    p.clone().appendTo(h);
    p.remove();
}
function removeAlls(obj){
    var p=$(obj).parent().next().find("#pickListResult option");
    var h=$(obj).parent().prev().children("#pickData");
    p.clone().appendTo(h);
    p.remove();
}
/**添加单个部门*/
function add(obj){
    var p = $(obj).parent().prev().find("#pickData option:selected");
    var h=$(obj).parent().next(".col-sm-5").children("#pickListResult");
    p.clone().appendTo(h);
    p.remove();
}
/**添加所有部门*/
function addAll(obj){
    var p = $(obj).parent().prev().find("#pickData option");
    var h=$(obj).parent().next(".col-sm-5").children("#pickListResult");
    p.clone().appendTo(h);
    p.remove();
}


