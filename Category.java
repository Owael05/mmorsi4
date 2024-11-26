public class Category extends Entity {
    private int id;
    private String name;
    private String description;
    private Table products;

    // CONSTRUCTORS

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nDescription: " + description + "\nProducts: " + products.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id, Database db, Admin admin){
        Table<Category> categoryList = db.get(admin, "categories");

        for (int i = 0; i < categoryList.getnumberOfElements(); i++) {
            if(id == (categoryList.get(i)).id) {
                throw new AlreadyExists("Category ID already exists");
            }    
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void save(Database db, Admin admin){
        if(this.isSaved()){
            throw new AlreadySaved("Category already saved");
        }

        else{
            super.save(db, admin, "categories");
        }
    }

    public void delete(Database db, Admin admin){
        super.delete(db, admin, "categories");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Category){
            return this.id == ((Category)obj).id;
        }
        return false;
    }
}