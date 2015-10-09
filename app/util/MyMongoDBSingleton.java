package util;

import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.caluga.morphium.Morphium;
import de.caluga.morphium.MorphiumConfig;
import play.inject.ApplicationLifecycle;
import play.libs.F;

@Singleton
public class MyMongoDBSingleton {
	private  Morphium m;
	private  MorphiumConfig cfg;
	
	@Inject
	public MyMongoDBSingleton(ApplicationLifecycle lifecycle) {
		cfg = new MorphiumConfig();
		cfg.setDatabase("play");// db name is play
		try {
			cfg.addHost("localhost", 27017);
			m = new Morphium(cfg);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			m = null;
			cfg = null;
		}
		
		lifecycle.addStopHook(() -> {
			if(m != null){
				m.close();
				
			}
			return F.Promise.pure(null);
		});
	}
	
	 public Morphium getMorphium(){
		 return m;
	 }
}
