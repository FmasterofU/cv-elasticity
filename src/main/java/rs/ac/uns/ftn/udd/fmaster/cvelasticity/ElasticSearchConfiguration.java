package rs.ac.uns.ftn.udd.fmaster.cvelasticity;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
public class ElasticSearchConfiguration {

	    
		@Value("${elasticsearch.host}")
	    private String EsHost;

	    @Value("${elasticsearch.port}")
	    private int EsPort;

	    @Value("${elasticsearch.clustername}")
	    private String EsClusterName;
	    
		public Client nodeClient() {
			Settings settings = Settings.builder()
					.put("path.home", "data")
					.build();
			
			final Node node = new NodeBuilder()
					.settings(settings)
					.local(true)
			        .build().start();
			
			return node.client();
		}
	    
	    @Bean
	    public ElasticsearchOperations elasticsearchTemplate() {
	        return new ElasticsearchTemplate(nodeClient());
	    }

}
