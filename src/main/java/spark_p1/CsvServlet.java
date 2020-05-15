package spark_p1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.PairFunction;


import scala.Function;
import scala.Tuple2;

public class CsvServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	private JavaRDD<String> local_cache;
	
	private String price=null;
	private String name=null;
	private String city=null;
	private String state=null;
	private String country=null;
	private String filter="false";
	
	
	
	public CsvServlet(JavaRDD<String> data)
	{
		this.local_cache=data;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.filter=req.getParameter("filter");
		this.name=req.getParameter("name");
		this.price=req.getParameter("price");
		this.city=req.getParameter("city");
		this.state=req.getParameter("state");
		this.country=req.getParameter("country");


		if(req.getParameter("name")!=null && filter.equals("false"))
		{
			JavaPairRDD<String,Integer> pair=nameCount();
			for(Tuple2 s:pair.collect())
			{
				resp.getWriter().println("<p>"+s+"</p>");
			}
		}
		if(req.getParameter("city")!=null && filter.equals("false"))
		{
			JavaPairRDD<String,Integer> pair=cityCount();
			for(Tuple2 s:pair.collect())
			{
				resp.getWriter().println("<p>"+s+"</p>");
			}
		}
		if(req.getParameter("state")!=null && filter.equals("false"))
		{
			JavaPairRDD<String,Integer> pair=stateCount();
			for(Tuple2 s:pair.collect())
			{
				resp.getWriter().println("<p>"+s+"</p>");
			}
		}
		if(req.getParameter("country")!=null && filter.equals("false"))
		{
			JavaPairRDD<String,Integer> pair=countryCount();
			for(Tuple2 s:pair.collect())
			{
				resp.getWriter().println("<p>"+s+"</p>");
			}
		}
		if(this.filter.equals("true"))
		{
			List<String> transform=filter();
			resp.getWriter().println("Filter");
			for(String s: transform)
			{
				resp.getWriter().println("<p>"+s+"</p>");
			}
		}
		
		
		
	}
	public List<String> filter()
	{	List<String> result=new ArrayList<>();
		
		for(String s:local_cache.collect())
		{
			
			String a[]=s.split(",");
			if(a[2].equals(this.price) || a[4].equals(this.name)||a[5].equals(this.city)||a[6].equals(this.state)||a[7].equals(this.country))
			{
				result.add(s);
			}
		}
		return result;
	}

	public JavaPairRDD<String,Integer> countryCount()
	{
		JavaPairRDD<String,Integer> pair=local_cache.mapToPair(new PairFunction<String,String,Integer>()
		{
			@Override
			public Tuple2<String,Integer> call(String input)throws Exception{
				return new Tuple2<String,Integer>(input.split(",")[7],1);
			}
		}
		);
		pair=pair.reduceByKey((x,y)->x+y);
		return pair;
	}
	public JavaPairRDD<String,Integer> stateCount()
	{
		JavaPairRDD<String,Integer> pair=local_cache.mapToPair(new PairFunction<String,String,Integer>()
		{
			@Override
			public Tuple2<String,Integer> call(String input)throws Exception{
				return new Tuple2<String,Integer>(input.split(",")[6],1);
			}
		}
		);
		pair=pair.reduceByKey((x,y)->x+y);
		return pair;
	}
	public JavaPairRDD<String,Integer> cityCount()
	{
		JavaPairRDD<String,Integer> pair=local_cache.mapToPair(new PairFunction<String,String,Integer>()
		{
			@Override
			public Tuple2<String,Integer> call(String input)throws Exception{
				return new Tuple2<String,Integer>(input.split(",")[5],1);
			}
		}
		);
		pair=pair.reduceByKey((x,y)->x+y);
		return pair;
	}
	public JavaPairRDD<String,Integer> nameCount()
	{
		JavaPairRDD<String,Integer> pair=local_cache.mapToPair(new PairFunction<String,String,Integer>()
		{
			@Override
			public Tuple2<String,Integer> call(String input)throws Exception{
				return new Tuple2<String,Integer>(input.split(",")[4],1);
			}
		}
		);
		pair=pair.reduceByKey((x,y)->x+y);
		return pair;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("doPost of CsvServlet");
	}
	

}
