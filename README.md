# SpringDataJPA-Relationship-SpringBoot
## A article web jpa, including author, article, topic, wallet, comment.
### The Author can post article, topic, comment, and also the can tip another one for that we have a wallet for every author.
Here is the mySQL EER:
![SQLEER](src/main/resources/image/JianShuEERDiagram.png)
### Attention:
1. Use @Transactional to use hibernate session, in the case where are many methods if one fail, all the data will roll back, this annotation has to be at serviceImplement: ![trasactional](src/main/resources/image/tansactional.png)
2. The order in which the components are run is: Entity -> Repository -> ServiceImplement -> Service(interface) -> Controller: ![order](src/main/resources/image/order.png)
3. configuration.yml: Use (jpa.hibernate.ddl-auto: update) to auto create table and their relationship(not recommended): ![yaml](src/main/resources/image/yml.png)
4. Add @JsonIgnore at foreign filed to fix (StackOverflow) ERROR, like: ![OneToMany](src/main/resources/image/OneToMany.png)![ManyToOne](src/main/resources/image/ManyToOne.png)
5. The maintainer Entity need to add mappedBy =? at filed like: ![mappedBy](src/main/resources/image/mappedByCapture.png)
6. The maintainer can add (cascade = {CascadeType.All,CascadeType.PERSIST}) to cascade relationship: ![mappedBy](src/main/resources/image/cascade.png)

