select o.animal_id, o.name
from animal_outs o join animal_ins i
on i.animal_id = o.animal_id
order by o.datetime - i.datetime desc limit 2