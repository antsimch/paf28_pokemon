package sg.edu.nus.iss.paf28_pokemon.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonRepository {
    
    private MongoTemplate template;

    public PokemonRepository(MongoTemplate template) {
        this.template = template;
    }

    /*
     * db.pokemon.aggregate([
     *  {
     *      $unwind: "$type"
     *  },
     *  {
     *      $group: {
     *          _id: "$type"
     *      }
     *  },
     *  {
     *      $sort: {_id: 1}
     *  }
     * ])
     */
    public List<Document> findAllTypes() {

        AggregationOperation unwindTypes = Aggregation.unwind("type");
        
        GroupOperation groupByType = Aggregation.group("type");
        
        SortOperation sortByType = Aggregation.sort(Sort.by(Direction.ASC, "_id"));
                
        Aggregation pipeline = Aggregation.newAggregation(unwindTypes, groupByType, sortByType);

        return template.aggregate(pipeline, "pokemon", Document.class).getMappedResults();
    }

    /*
     * db.pokemon.find({
     *  type: {
     *      $in: [type]
     *  } 
     * })
     */
    public List<Document> findPokemonByType(String type) {
        Query query = Query.query(Criteria.where("type").in(type));
        return template.find(query, Document.class, "pokemon");
    }
}
