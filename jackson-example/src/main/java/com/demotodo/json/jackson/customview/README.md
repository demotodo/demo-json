Hi. Instead of using class-type filtering I would like to do
instance-based one. For example I have classes:

```
class A {
 String name;
 B  info;
 A child;
}
class B {
 int distance;
 String id;
}

A root = new A();
}}}
```

Now, I would like to serialize instance root in a following way: serialize all fields that belong to the root but for root.child serialize only name. So result would be:

```
  "root": {
     "name": "somthing",
     "info": { "distance":100, "id":"123"},
     "child": {"name": "lalala"}
   }
}}}
```


Any ideas whats the easiest way to pass filtering object along during serialization?
Filtering object will probably Map object.
