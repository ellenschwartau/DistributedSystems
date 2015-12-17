-module(clock).
-export([run/2, start/1, get/1, runTimer/2]).

run(Time, Speed)->
	receive
		{set, Value} -> run(Value, Speed);
		{get,Pid} -> Pid!{clock,Time}, run(Time,Speed);
		incr -> run(Time+1, Speed);
		stop -> exit(normal)
	end.

% Unterprozess, der für das Zählen verantwortlich ist
runTimer(Speed,Pid)->
	receive
	after Speed -> Pid!incr, runTimer(Speed,Pid)
	end.

start(Speed) ->
	ClockPid = spawn(?MODULE, run, [0, Speed]),
	spawn(?MODULE, runTimer, [Speed, ClockPid]), ClockPid.

get(Clock)->
	Clock ! {get,self()},
	receive
		{clock,Time} -> io:format("Uhrzeit: ~p~n", [Time])
	end.
