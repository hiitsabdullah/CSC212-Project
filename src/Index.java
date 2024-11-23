

public class Index {
    class Document {
            int docID;
            LinkedList <String> index; 

            public Document() {
                docID = 0;
                index = new LinkedList <String>();
            }

            public void addNew (String word)
            {
                index.insert(word);
            }

           public boolean found(String word)
           {
               if (index.empty())
                   return false;

               index.findFirst();
               for ( int i = 0 ; i < index.size ; i++)
               {
                   if ( index.retrieve().compareTo(word) == 0)
                       return true;
                  index.findNext();
               }
               return false;
           }
    }   
    //===========================================================
    
    Document [] indexes;

    public Index() {

        indexes = new Document [50];
        for ( int i = 0 ; i < indexes.length ; i++)
        {
            indexes [i] = new Document();
            indexes [i].docID = i;
        }
    }
        
    public void addDocument ( int docID, String data)
    {
            indexes[docID].addNew(data);
    }
    
    public void printDocment (int docID)
    {
        if ( indexes[docID].index.empty())
            System.out.println("Empty Document");
        else
        {
            indexes[docID].index.findFirst();
            for ( int i = 0; i< indexes[docID].index.size ; i++)
            {
                System.out.print (indexes[docID].index.retrieve() + " ");
                indexes[docID].index.findNext();
            }
        }
    }
//=================================================================
public  boolean [] getDocs (String str)
{
    boolean [] result = new boolean [50];
    for (int i = 0 ; i < result.length ; i++)
        result[i] = false;
    
    for (int i = 0 ; i < result.length ; i++)
        if (indexes[i].found(str))
            result[i] = true;

    return result;
}

//=================================================================
        public boolean [] AND_OR_Function (String str )
        {
            if (! str.contains(" OR ") && ! str.contains(" AND "))
            {
                str = str.toLowerCase().trim();
                boolean [] r1 = getDocs(str.toLowerCase().trim());
                return r1;
            }
            
            else if (str.contains(" OR ") && str.contains(" AND "))
            {
                String [] AND_ORs = str.split(" OR ");
                boolean []  r1 = AND_Function (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] r2 =AND_Function (AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        r1 [j] = r1[j] || r2[j];
                }
                return r1;
            }
            
            else  if (str.contains(" AND "))
                return AND_Function (str);
            
            return OR_Function (str);
        }

        //==========================================================================
        public boolean [] AND_Function (String str)
        {
            String [] ANDs = str.split(" AND ");
            boolean [] b1 = getDocs(ANDs[0].toLowerCase().trim());

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] b2 = getDocs(ANDs[i].toLowerCase().trim());
                for ( int j = 0 ; j < 50 ; j++)
                    b1 [j] = b1[j] && b2[j];
            }                
            return b1;
        }

         public boolean [] OR_Function (String str)
        {
            String [] ORs = str.split(" OR ");
            boolean [] b1 = getDocs(ORs[0].toLowerCase().trim());

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] b2 = getDocs(ORs[i].toLowerCase().trim());
                for ( int j = 0 ; j < 50 ; j++)
                    b1 [j] = b1[j] || b2[j];
            }
            return b1;
        }

}
