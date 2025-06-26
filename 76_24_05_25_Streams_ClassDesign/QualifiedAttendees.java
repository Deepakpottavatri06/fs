// Given a list of EventAttendance (eventId, attendeeName, durationMinutes), 
// Your task is to consider only attendees who stayed ≥ 60 minutes.
// For each event, display the Event ID (ascending order), List of qualified 
// attendee names (alphabetically sorted) and Count of such attendees.

// Example 1
// ---------
// Sample Input:
// 4
// E101 John 90
// E101 Alice 55
// E101 Zara 75
// E102 Mark 120

// Sample Output:
// E101 [John, Zara] Count=2
// E102 [Mark] Count=1

// Example 2
// ---------
// Sample Input:
// 11
// E502 Carl 90
// E502 Dan 45
// E501 Ana 100
// E502 Evan 75
// E501 Beth 61
// E502 Fred 20
// E301 Ron 30
// E301 Tony 60
// E302 Lily 75
// E302 Kevin 50
// E301 Maya 90

// Sample Output:
// E301 [Maya, Tony] Count=2
// E302 [Lily] Count=1
// E501 [Ana, Beth] Count=2
// E502 [Carl, Evan] Count=2

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
class Person{
    String id;
    String name;
    int min;
    Person(String id, String name, int min){
        this.id = id;
        this.name = name;
        this.min = min;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getMin(){
        return this.min;
    }
}
public class QualifiedAttendees{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            List<Person> list=  new ArrayList<>();
            for(int i = 0; i < n; i++){
                String id = cin.next();
                String name = cin.next();
                int min = cin.nextInt();
                list.add(new Person(id,name,min));
            }
            
            list.stream().
            filter(x->x.getMin()>=60).collect(
                Collectors.groupingBy(
                    Person::getId,
                    Collectors.mapping(Person::getName,Collectors.toList())
                    )
                ).entrySet().stream().
                sorted((a,b)->a.getKey().compareTo(b.getKey()))
                .forEach(entry ->{
                    List<String> names = entry.getValue();
                    Collections.sort(names);
                    System.out.println(entry.getKey() + " " + names + " " + "Count=" + names.size());
                });
        }
            
       
        
    }
}