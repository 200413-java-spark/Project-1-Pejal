package spark_p1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Server {
	static private List<Transaction_info> history=new ArrayList<>();
	public static void main(String[] args) throws LifecycleException
	{
		SparkConf conf=new SparkConf().setMaster("local").setAppName("Project 1 Pejal");
		JavaSparkContext sparkContext=new JavaSparkContext(conf);//error occur here
		JavaRDD<String> data=sparkContext.textFile("SalesJan2009.csv");
		addToHistory(data);
		writeDB(history);
		int i=0;
		for(Transaction_info s:history)
		{
			if(i>20)
				break;
			System.out.println(s);
			i++;
		}
		
		
		Tomcat tomcat=new Tomcat();
		tomcat.setBaseDir(new File("target/tomcat/").getAbsolutePath());
		tomcat.setPort(8080);
		tomcat.getConnector();
		tomcat.addWebapp("/spark_p1", new File("src/main/webapp").getAbsolutePath());
		tomcat.addServlet("/spark_p1", "CsvServlet", new CsvServlet()).addMapping("/csv");;
		tomcat.start(); //error occur here
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					sparkContext.close();
					tomcat.stop();
				}catch(LifecycleException e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}
	public static void writeDB(List<Transaction_info> data)
	{
		SqlDataSource ds=SqlDataSource.getInstance();
		SqlSparkRepository dRep=new SqlSparkRepository(ds);
		dRep.insertAll(data);
	}
	public static void addToHistory(JavaRDD<String> data)
	{
		List<String> temp_data=rddToList(data);
		for(String s:temp_data)
		{
			Transaction_info temp=new Transaction_info();
			String a[]=s.split(",");
			
			temp.setTranDate(a[0]);
			temp.setProduct(a[1]);
			temp.setPrice(a[2]);
			temp.setPayType(a[3]);
			temp.setName(a[4]);
			temp.setCity(a[5]);
			temp.setState(a[6]);
			temp.setCountry(a[7]);
			temp.setCreate(a[8]);
			temp.setLogin(a[9]);
			temp.setLatitude(a[10]);
			temp.setLongitude(a[11]);
			history.add(temp);
		}
	}
	public static List<String> rddToList(JavaRDD<String> data)
	{
		List<String> result=new ArrayList<>();
		for(String s:data.collect())
			result.add(s);
		return result;
	}
}
