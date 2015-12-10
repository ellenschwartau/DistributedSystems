-module(counter).
-export([loop/1, start/0, get/1, set/2]).

loop(C) ->
   receive 
       reset -> loop(0);
       up -> loop(C+1);
       down -> if 
       			(C==0) -> exit(underflow); 
       			true -> loop(C-1) 
       		   end;
       {get, Who, Tag} -> Who ! {counter_value, Tag, C}, loop(C);
       {set, Value} -> loop(Value);
       show -> io:format("Counter: ~w~n", [C]), loop(C);
       stop -> exit(normal)
   end.
   
start() -> spawn(?MODULE, loop, [0]).

get(Pid) ->  
    Tag = erlang:now(), 
    Pid ! {get, self(), Tag},
    receive
       {counter_value, Tag, C} -> C
       after 10000 -> timeout
    end.
    
set(Pid, Value) ->
    Pid ! {set, Value} .
    
echo(Pid) ->
	X -> io:format("~p~n", X),
		 Pid ! X .



% on_exit(Pid, fun(Why) -> io:format(" ~p died with:~p~n",[Pid, Why]) end)