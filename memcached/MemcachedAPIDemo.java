/*
 * Description : An example of using memcached java client API
 */
import java.util.HashMap;
import java.util.concurrent.Future;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedAPIDemo {

	public static void main(String[] args) {
		try{
			// Case : connect to local memcached server
			MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 12111));
			System.out.println("Connection to server sucessful.");

			// Case : Store data, if key was exist, still update it's value
			// key1 : 1
			Future fo = mcc.set("key1", 900, "1");
			System.out.println("set cmd's status:" + fo.get());   // Get status

			// Case : Get data
			System.out.println("key1's value : " + mcc.get("key1"));

			// Case : Add data, if key was exist, then return NOT_STORED
			// key1 : 1
			// key2 : value2
			fo = mcc.add("key2", 900, "value2");
			System.out.println("First add cmd's status : " + fo.get());

			fo = mcc.add("key2", 900, "value2a");
			System.out.println("Second add cmd's status : " + fo.get());

			// Case :Replace data, if key was exist, then return NOT_STORED
			// key1 : 1
			// key2 : value2b
			fo = mcc.replace("key2", 900, "value2b");
			System.out.println("replace cmd's status : " + fo.get());

			// Case : prepend and append
			// key1 : 1
			// key2 : pre value2b after
			fo = mcc.prepend("key2", "pre ");
			System.out.println("prepend cmd's status : " + fo.get());
			fo = mcc.append("key2", " after");
			System.out.println("key2's value : " + mcc.get("key2"));
			System.out.println("apppend cmd's status : " + fo.get());

			// Case : incr and decr key1's value
			// key1 : 1 --> 6 --> 4
			// key2 : pre value2b
			System.out.println("key1's value : " + mcc.incr("key1", 5));
			System.out.println("key1's value : " + mcc.decr("key1", 2));

			// Case : Delete key-value
			// key1 : 4
			fo = mcc.delete("key2");
			System.out.println("delete cmd's status : " + fo.get());

			// Case : Close connection with Memcached Server
			mcc.shutdown();

		}catch(Exception ex){

			System.out.println(ex.getMessage());

		}
	}
}
