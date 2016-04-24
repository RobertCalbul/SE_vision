%A
%B
%C
es_sintoma(cansansio_trabajo). %cansancio ojos en el trabajo
%D
es_sintoma(dolor_cabeza).
es_sintoma(dolor_ocular).
es_sintoma(d_letra_chica). %dificultad para ver letra chica
es_sintoma(d_objeto_cerca).%dificultad ver objetos cercanos
es_sintoma(d_objeto_lejo). %dificultad ver objetos lejanos
es_sintoma(d_conducir_noche). %dificultad para conducir de noche
%E
es_sintoma(entrecerrar_ojos).
%F
es_sintoma(fatiga_visual).
%G
%H
%I
es_sintoma(irritacion).% despues de forzar vista
%L
es_sintoma(luz_deslumbrante).
%M
es_sintoma(mayor_distancia). %ajelar objetos
%N
%O
es_sintoma(ojos_bizcos).
es_sintoma(ojos_enrojecidos).
es_sintoma(ojos_hinchados).
es_sintoma(ojos_cansados).
%P
%Q
%R
%S
%T
es_sintoma(tension_ojos). %aumento tension ocular
%U
%V
es_sintoma(vision_doble).
es_sintoma(vision_nublada).
es_sintoma(vision_borrosa).
es_sintoma(vision_distorcionada).
%W
%X
%Y
%Z
%%---MIOPIA---%%
sintoma_de(dolor_cabeza     , miopia).
sintoma_de(entrecerrar_ojos , miopia).
sintoma_de(fatiga_visual    , miopia).
sintoma_de(d_objeto_lejo    , miopia).
sintoma_de(tension_ojos     , miopia).
sintoma_de(irritacion       , miopia).
sintoma_de(cansansio_trabajo, miopia).
%%---HIPERMETROPIA---%%
sintoma_de(dolor_cabeza    , hipermetropia).
sintoma_de(fatiga_visual   , hipermetropia).
sintoma_de(entrecerrar_ojos, hipermetropia).
sintoma_de(vision_borrosa  , hipermetropia).
sintoma_de(dolor_ocular    , hipermetropia).
sintoma_de(ojos_bizcos     , hipermetropia).
%%---ASTIGMATISMO---%%
sintoma_de(dolor_cabeza        , astigmatismo).
sintoma_de(fatiga_visual       , astigmatismo).
sintoma_de(entrecerrar_ojos    , astigmatismo).
sintoma_de(vision_borrosa      , astigmatismo).
sintoma_de(vision_distorcionada, astigmatismo).
sintoma_de(d_conducir_noche    , astigmatismo).
sintoma_de(ojos_enrojecidos    , astigmatismo).
sintoma_de(ojos_hinchados      , astigmatismo).
sintoma_de(ojos_cansados       , astigmatismo).
%%---PRESBICIA---%%
sintoma_de(d_letra_chica  , presbicia).
sintoma_de(mayor_distancia, presbicia).
sintoma_de(d_objeto_cerca , presbicia).
sintoma_de(dolor_cabeza   , presbicia).
sintoma_de(fatiga_visual  , presbicia).

problema_de(X,Y) :- es_sintoma(X), sintoma_de(X,Y).



