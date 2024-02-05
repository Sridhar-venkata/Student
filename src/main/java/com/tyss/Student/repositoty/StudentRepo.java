package com.tyss.Student.repositoty;

import com.tyss.Student.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepo extends MongoRepository<Student, String> {

  @Query("{'firstName':?0,'lastName':?1}")
  public List<Student> findBothFistAndLast(String firstName, String lastName);

  public Student findByEmail(String email);

  @Aggregation(pipeline = {
    "{$match: { 'favSubjects': {$in:?0}}}",
    "{$addFields: { 'matchCount': { $size: { $setIntersection: [ ?0, '$favSubjects' ] } } }}",
    "{$sort: { 'matchCount': -1 }}"
  })
  List<Student>  findStudentsByFavSubjects(List<String> favSubjects);


  @Query(value = "{'_id':?0}", fields = "{'favSubjects':1,'_id':0}")
  List<String> fetchFavSubjects(String id);

  @Query("{firstName}:?0")
  List<Student> findSort(String name, Sort sort);
}
