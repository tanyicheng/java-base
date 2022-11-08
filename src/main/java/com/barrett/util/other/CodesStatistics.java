package com.barrett.util.other;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

/**
 * 	《代码统计》
 * 	统计从用Eclipse以来，到目前为止，一共写了多少行有效代码？有多少行注释？?有多少空行？?有多少个字符？
 *	思路：
 *		- 文件递归遍历
 *		- 文件过滤(找所有Java文件)
 *		- 读取每个文件的内容
 *		- 使用正则表达式匹配换行
 * 
 * @author mrchai
 */
public class CodesStatistics {

	/**java文件的容器（一个List表，泛型为String）*/
	private List<String> javaFiles;
	
	/**换行数*/
	private Pattern patternLines;
	/**空白行数*/
	private Pattern patternWhiteSpaceLines;
	/**注释行数*/
	private Pattern patternCommentLines;

	/**初始化块里对正则表达式编译、过滤*/
	{
		// 匹配行表达式
		// 判断代码总共有多少行就靠这个东西！有多少行代码就有多少行\n！
		patternLines = Pattern.compile("\\n");//\n换行符匹配，只要换行，就有\n，但是正则表达式编译时得是\\n
		// 匹配空白行表达式
		patternWhiteSpaceLines = Pattern.compile("\\n[\\s| ]*\\n");//\s匹配任何空白字符，包括空格、制表符、换页符等。
																//*代表：零次或多次匹配前面的字符或子表达式。例如，zo* 匹配"z"和"zoo"。* 等效于 {0,}。
		// 匹配注释表达式
		patternCommentLines = Pattern.compile("(//)|(/\\*)|(\\*)");//注释就是以//或者/*或者*开头的一行
	}

	/**
	 * 	构造器，参数为File类型的根目录basedir
	 */
	public CodesStatistics(File baseDir) {
		// 容器初始化（定义泛型为String）
		javaFiles = new ArrayList<String>();
		// 读取Java文件到集合（调用readJavaFilesToList方法）
		readJavaFilesToList(baseDir);
		// 文件总数（Java.util.List中的size()方法：返回此列表中的元素数）
		System.out.println(suffix+"文件总数:" + javaFiles.size() + "个");
	}

	/**
	 * 	读取所有Java文件到集合中
	 * 
	 * @param dir
	 */
	public void readJavaFilesToList(File dir) {
		//创建一个储存files的数组
		File[] files = dir.listFiles();
		if (files != null) {
			//循环编译文件数组（循环次数是files数组长度，也就是files数组中文件的个数）
			for (int i = 0; i < files.length; i++) {
				//根据索引“取出”文件数组元素，并定义一个File类型的f接收
				File f = files[i];
				//判断这个元素是否是文件，且，是否是java文件（调用isJavaFile()方法判断是否是java文件）
				if (f.isFile() && isJavaFile(f)) {
					// 将文件绝对路径添加到javaFiles集合
					javaFiles.add(f.getAbsolutePath());//java.io.File类中的getAbsolutePath()方法：返回此抽象路径名的绝对路径名字符串。
				//如果不是文件（即是文件夹）
				} else {
					// 对目录递归
					readJavaFilesToList(f);
				}
			}
		}
	}

	/**
	 *	 判断指定文件是否是Java文件（传入的参数f是files数组中的元素（先不确定是目录、普通文件还是需找的java文件））
	 * 
	 * @param f
	 * @return
	 */
	public boolean isJavaFile(File f) {
		//java.io.File类中的getName()方法：返回由此抽象路径名表示的文件或目录的名称。 
		//java.lang.String类中的toLowerCase()方法：将所有在此字符 String使用默认语言环境的规则，以小写。 
		//java.lang.String类中的endsWith()：测试此字符串是否以指定的后缀结尾。 
		return f.getName().toLowerCase().endsWith("."+suffix);
	}

	/**
	 *	 匹配所有代码行（统计所有代码行数）
	 * 
	 * @return
	 */
	public int countLines() throws IOException {
		//声明计数器以统计所有代码行数
		int count = 0;
		//遍历javaFiles集合，遍历次数为集合元素个数
		for (int i = 0; i < javaFiles.size(); i++) {
			//readToString()：转成字符串类型
			//java.util.List类中的get()方法：根据索引，返回此列表中指定位置的元素。 
			String content = FileUtils.readFileToString(new File(javaFiles.get(i)),"utf-8");//comtent：内容、含量、目录
			//java.util.regex.Pattern类中的matcher()方法：创建一个匹配器，匹配给定的输入与此模式。返回的是一个Matcher类型
			Matcher matcher = patternLines.matcher(content);
			//java.util.regex.Mathcer类中的find()方法：尝试找到匹配模式的输入序列的下一个子序列。 返回的是Boolean值
			while (matcher.find()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 	匹配空白代码行（统计空白代码行数）
	 * 
	 * @return
	 */
	//实现原理同上方法
	public int countWhiteSpaceLines() throws IOException {
		int count = 0;
		for (int i = 0; i < javaFiles.size(); i++) {
			String content = FileUtils.readFileToString(new File(javaFiles.get(i)),"utf-8");
			Matcher matcher = patternWhiteSpaceLines.matcher(content);
			while (matcher.find()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 	匹配注释代码行（统计注释代码行数）
	 * 
	 * @return
	 */
	//实现原理同上方法
	public int countCommentLines() throws IOException {
		int count = 0;
		for (int i = 0; i < javaFiles.size(); i++) {
			String content = FileUtils.readFileToString(new File(javaFiles.get(i)),"utf-8");
			Matcher matcher = patternCommentLines.matcher(content);
			while (matcher.find()) {
				count++;
			}
		}
		return count;
	}

	/**
	 *	 有效代码行（总代码行数-空白行数-注释行数）
	 * 
	 * @return
	 */
	//实现原理同上方法
	public int countValidLines() throws IOException {
		return countLines() - countWhiteSpaceLines() - countCommentLines();
	}


	public final static String suffix = "js";
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("请输入需要查找代码的目录：");
//		String s = sc.nextLine();

		String s = "/Users/snipe/Documents/tan/git/project/FitnessPlan/app/src";
//		String s = "/Users/snipe/Documents/tan/git/study/activiti5-demo";

		File dir = new File(s);
		CodesStatistics cs = new CodesStatistics(dir);

		int lines1 = cs.countLines();
		System.out.println("总代码行数:" + lines1);

		int lines2 = cs.countWhiteSpaceLines();
		System.out.println("空白代码行数:" + lines2);

		int lines3 = cs.countCommentLines();
		System.out.println("注释代码行数:" + lines3);

		int lines4 = cs.countValidLines();
		System.out.println("有效代码行:" + lines4);
	}
}

//知识梳理：
/* Pattern类可以编译字符串（正则表达式），一次编译后可以反复使用编译后的表达式，提高查找效率，简化操作流程。*/
/* 小知识：java中所有正则表达式在使用之前都是经过编译了的（编译：compile），编译过程，就是通过Pattern类来实现。*/
/* Pattern类无法直接实例化，Pattern类没有提供公开的*/
/* 而Matcher：提供对已经编译过的正则表达式，匹配字符串功能。*/
/* Pattern典型的调用序列：
 *	Pattern p = Pattern.compile("a*b");
 *	Matcher m = p.matcher("aaaaab");
 *	boolean b = m.matches();
 *	以上三行代码，其实在String里面都封装起来了，其实就是matches方法。
 */
/*	利用正则表达式的编译筛查功能，通过\n和\s等字符编译方法，计算各类代码行数量，从而确定最终各类代码行数的数量*/
