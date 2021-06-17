/**
 * 
 */
package process_test_package;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Process.ProcessRun;





/**
 * @author Itai
 *
 */
class ProcessTest {

	String output ;
	ProcessRun process ;
	String[] command = {"C:\\QA_Tirgol\\CshConsoleAPI\\bin\\Debug\\CshConsoleAPI.exe",""};
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Setup before every test");
		output = "";
		process = new ProcessRun();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	System.out.println("After every test test");
	}


	/***
	 * Test the stack trace print is working.
	 */
	@Test
	void testTrace() {	
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String trace1 = sw.toString();
			System.out.print(trace1);
			
			Assert.assertTrue(!trace1.isEmpty());
		}
	}


	/***
	 * Test operating system command line.
	 * Run command line sub process.
	 */	
	@Test
	void testCmdDir() {
		
		try {
			String[] dir_rist = {"dir", "c:\\"};
			process.Execute(dir_rist);
			process = null;
		} catch (Exception e) {
			fail("Unable to run dir");
		}
		return;
	}
	
	
	/***
	 * Test the console application runs.
	 */
	@Test
	void testConsole() {
		try {
			ProcessRun process = new ProcessRun();
			output = process.Run(command);
			System.out.println(output);
						
			output = process.Exit();			
		}
		catch (Exception e) {
			//	In case of exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			//	Read the trace information.
			e.printStackTrace(pw);
			String trace = sw.toString();
			
			//	Write the trace to console and fail the test.
			System.out.print(trace);
			Assert.assertTrue(!trace.isEmpty());
		}
		return;
	}

	
	/***
	 * Test the console application runs 
	 * and execute command-line multiple commands.
	 */
	@Test
	void testCommands() {
		
		try {
			output = process.Run(command);
			Assert.assertTrue(output.contentEquals("\n>"));
			
			output = process.Command("echo(Hello world)\n");
			Assert.assertTrue(output.contentEquals("Hello world\r\n\n>"));
			
			output = process.Command("mul(4,6)\n");
			Assert.assertTrue(output.contentEquals("24\r\n\n>"));
			
			output = process.Command("add(4,6)\n");
			Assert.assertTrue(output.contentEquals("10\r\n\n>"));
			
			output = process.Command("sub(8,6)\n");
			Assert.assertTrue(output.contentEquals("2\r\n\n>"));
			
			output = process.Command("div(4,2)\n");
			Assert.assertTrue(output.contentEquals("2\r\n\n>"));

			output = process.Exit();			
		}
		catch (Exception e) {
			//	In case of exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			//	Read the trace information.
			e.printStackTrace(pw);
			String trace = sw.toString();
			
			//	Write the trace to console and fail the test.
			System.out.print(trace);
			Assert.assertTrue(!trace.isEmpty());
		}
		return;
	}

	

}

