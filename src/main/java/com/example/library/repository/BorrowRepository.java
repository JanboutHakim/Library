package com.example.library.repository;

import com.example.library.model.BorrowingRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowingRecords,Long> {
    @Modifying
    @Query(nativeQuery = true ,value = " UPDATE library.borrowing_records r SET " +
            "  r.return_date = :return_date " +
            " WHERE r.patron_id = :patron_id  " +
            " AND r.book_id = :book_id  ;")
    void returnBook(@Param("return_date")LocalDate returnedDate,
                    @Param("book_id") long bookID,
                    @Param("patron_id") long patronID);

    @Query(nativeQuery = true,value = "SELECT * FROM library.borrowing_records r WHERE r.book_id = :book_id " +
            "   and    " +
            "r.patron_id = :patron_id and r.return_date is null;")
    List<BorrowingRecords> findByIdPatronIDAndBookID(@Param("book_id") long bookID,
                                                     @Param("patron_id") long patronID);



}
