package com.barrett.gof.桥接模式;

/**
 * 直立手机 扩展的
 * @author created by barrett in 2021/1/25 20:33
 **/
public class UpRightPhone extends Phone {
	

		public UpRightPhone(Brand brand) {
			super(brand);
		}

		@Override
        public void open() {
			super.open();
			System.out.println("直立手机 open");
		}

		@Override
        public void close() {
			super.close();
			System.out.println("直立手机 close");
		}

		@Override
		public void call() {
			super.call();
			System.out.println("直立手机 call");
		}
}
