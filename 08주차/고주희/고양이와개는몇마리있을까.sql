SELECT ANIMAL_TYPE, count(*) count from ANIMAL_INS
group by ANIMAL_TYPE
HAVING ANIMAL_TYPE = "Cat" || ANIMAL_TYPE = "Dog"
order by ANIMAL_TYPE; 
