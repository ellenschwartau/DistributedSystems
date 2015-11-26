-module(convertions).
-export([convert/2, maxitem/1, maxitemVerbose/1]).

% 25.b) Definieren Sie eine Funktion, die zwischen cm und inch Konvertieren kann.
-define(Factor, 0.393701).

convert(X, inch)->
	{X/?Factor,cm};
convert(X, cm)->
	{?Factor*X,inch};
convert(X, Unit)->
	{X, Unit, error_unknown_unit}.


% 25.b) Definieren Sie eine Funktion, die das größte Element einer Liste ermittelt

maxitem([])->0;
maxitem([H|[]])->H;
maxitem([H|T])-> 
	Max = maxitem(T),
	case H > Max of
		true -> H;
		false -> Max
 	end
.

% 25.c) Visualisieren Sie den rekursiven Verlauf Ihrer maxitems Funktion.
maxitemVerbose([])->io:format("~s ~n", ["Reksurions Ende"]),0;
maxitemVerbose([H|[]])->io:format("~s ~n", ["Reksurions Ende"]),H;
maxitemVerbose([H|T])-> 
	io:format("~s ~n", ["Steige ab in Rekursion ..."]),
	Max = maxitemVerbose(T),
	io:format("~s ~p ~s ~p~s ~n", ["Bestimme Maximum:", H, ">", Max, "?"]),
	case H > Max of
		true -> H;
		false -> Max
 	end
.


