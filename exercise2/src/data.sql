


SELECT T1.id, T1.name, T1.age
FROM T1
         LEFT JOIN T2 ON T1.id = T2.id
WHERE T2.id IS NULL
ORDER BY T1.id;


#Alternatively

SELECT T1.*
FROM T1
         LEFT JOIN T2 ON T1.id = T2.id
WHERE T2.id IS NULL
ORDER BY T1.id;



/*Explanation:
#SELECT T1.id, T1.name, T1.age: This selects the columns id, name, and age from T1.
#LEFT JOIN T2 ON T1.id = T2.id: This performs a left join between T1 and T2 on the id column. The left join includes all records from T1 and the matching records from T2.
#WHERE T2.id IS NULL: This filters out the rows where id is present in T2. Essentially, it keeps only the rows from T1 that do not have a corresponding id in T2.
/*ORDER BY T1.id: This orders the result set by the id column of T1.


