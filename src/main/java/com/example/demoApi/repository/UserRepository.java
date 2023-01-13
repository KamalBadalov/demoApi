package com.example.demoApi.repository;

import com.example.demoApi.domain.SexType;
import com.example.demoApi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where birth_date <= current_date - interval'18 years'", nativeQuery = true)
    Page<User> findAllAdultAuthors(Pageable pageable);

    Page<User> findBySexTypeAndIdIn(SexType sexType, List<Long> ids, Pageable pageable);

    @Query(value = "select exists (select * from users where phone_number = :phoneNumber)", nativeQuery = true)
    boolean islPhoneNumberExists(@Param("phoneNumber") Long phoneNumber);

    @Query(value = "select exists (select * from users where email = :email)", nativeQuery = true)
    boolean isEmailExists(@Param("email") String email);

    @Query(value = """
            select * from users where id in (select author_id from contents where author_id = users.id)
            and users.sex_type = :sexType
            offset :page * :size limit :size
            """,
            nativeQuery = true)
    List<User> findUsersHasContentBySexType(@Param("sexType") String sexType, @Param("page") int page, @Param("size") int size);

    @Query(
            value = """
                    select * from users where users.id
                    in (select author_id from contents
                        inner join comments on comments.content_id = contents.id
                        group by author_id, comments.content_id
                        having count(author_id) >= 3 and
                        comments.content_id
                        in (select id from content_genre as cg
                        inner join genres as g on g.id = cg.genre_id))
                    order by id desc
                    offset :page * :size limit :size
                    """,
            nativeQuery = true)
    List<User> findUsersWhoHasContentAndGenreAndComment(@Param("page") int page, @Param("size") int size);
}