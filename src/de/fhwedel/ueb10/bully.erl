-module(bully).
-eyports([bull/2]).
-import(lists,[foreach/2]).

bull(Id, Coordinator, Others)->
	receive
		{sendElection} -> sendElectionToOthers(Others)
	end.

% Senden von election–Nachrichten an alle Prozesse mit höherer Nummer 
sendElectionToOthers(Others)->
	lists:foreach(sendElection, Others).
sendElection(Pid)->
	Pid!{election}.

% Verarbeiten von election–Nachrichten
processElection(Id, Others)->
	lists:foreach([fun (X) -> sendElectionResponse(Id, X) end], others).
sendElectionResponse(Id, Other)->
	if 
		Id > Other -> Other!{ok};
		true -> Other!{election}
	end.

% Senden und Verarbeiten von coordinator–Nachrichten (mit Ausgabe des Wahlergebnisses)
 sendCoordinator(Id, Other)->
 	lists:foreach([func (X) -> X!{coordinator,Id}], others).
 	
 	
 	
 	