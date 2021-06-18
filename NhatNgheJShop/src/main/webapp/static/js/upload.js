upload={
	change(file, img){
		if(file.files.length > 0)
		{
			var reader = new FilerReader();
			reader.onload = function(){
				$(img).attr("src", reader.result);
			};
			reader.readAsDataURL(file.files[0]);
		}
	}
}