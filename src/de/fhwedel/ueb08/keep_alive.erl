-module(keep_alive).
-export([keep_alive/2, on_exit/2]).

keep_alive(Name, Fun) ->
   io:format("Registering new process!~n",[]),
   register(Name, Pid = spawn(Fun)),
   
   receive after 10000 -> ok end,
   io:format("Pid ~p is_process_alive: ~p~n",
       [Pid, erlang:is_process_alive(Pid)]),
      
   on_exit(Pid, fun(_Why) -> keep_alive(Name, Fun) end).

on_exit(Pid, Fun) -> 
   spawn(fun() ->
       process_flag(trap_exit, true), 
       io:format("Linking to Pid=~p~n",[Pid]),
       link(Pid),
       receive
         {'EXIT', Pid, Why} -> io:format("Got exit signal~n",[]), Fun(Why) 
       end
   end).