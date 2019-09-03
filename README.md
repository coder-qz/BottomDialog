# BottomDialog

![](https://i.imgur.com/ndxxWed.jpg)

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.zhangqinzhi:BottomDialog:1.0.0'
	}


###单个添加

	   new BottomListDialog.Builder(this, true)
                .addItem("拍照")
                .addItem("相册")
                .setOnItemClickListener((dialog, itemView, position) -> {
                    dialog.dismiss();

                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialogInterface) {
              dialogInterface.dismiss();
              Toast.makeText(MainActivity.this,"弹窗消失",Toast.LENGTH_SHORT).show();
          }
      }).setOutSideCancelable(false).build().show();

###添加集合

		List<String> arrayList =  new ArrayList<>();
        arrayList.add("第一个");
        arrayList.add("第二个");
        arrayList.add("第三个");
        new BottomListDialog.Builder(this, true)
                .addList(arrayList)
                .setOnItemClickListener((dialog, itemView, position) -> {

                    Toast.makeText(MainActivity.this,arrayList.get(position),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }).build().show();

###自定义对象
对象要实现IBottomData接口

	 	List<Person> arrayList =  new ArrayList<>();
        arrayList.add(new Person("ios",30));
        arrayList.add(new Person("android",24));
        arrayList.add(new Person("flutter",18));
        arrayList.add(new Person("kotlin",18));
        arrayList.add(new Person("go",18));
        arrayList.add(new Person("python",18));
        new BottomListDialog.Builder(this, true)
                .addList(arrayList)
                .setOnItemClickListener((dialog, itemView, position) -> {
                    Toast.makeText(MainActivity.this,arrayList.get(position).toString(),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }).build().show();