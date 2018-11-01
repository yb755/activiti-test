import java.io.IOException;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;


public class LeaderElectionTest {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
	      MyWatch myWatch = new MyWatch();

	      ZooKeeper zk = new ZooKeeper("192.168.1.236:4181",1000,myWatch);

	      myWatch.setZooKeeper(zk);

	      //先创建一个节点存储创建过的机器节点
	      try {
	         zk.create("/leader", ("主机").getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	      }catch (KeeperException.NodeExistsException e){
	         System.out.println("主机节点已创建");
	      }
	      //模拟新加入一台机器
	      Random r = new Random();
	      String num = r.nextInt(100000)+"";

	      String sequentialPath = zk.create("/leader/children",("运行server的ip:"+num).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

	      System.out.println("同步创建临时顺序节点成功：" + sequentialPath);

	      //注册监听事件
	      zk.getChildren("/leader",true);

	      //此处模拟机器持续运行着
	      while(true){

	      }
	   }
}
