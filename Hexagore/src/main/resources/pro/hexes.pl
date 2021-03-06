/* Generated from method: sn.thecells.entity.Hexagon.writePrologScript() */
/* NOTE: _base predicated should be override for better control */

:- dynamic(hex_type_base/2).
:- dynamic(hex_height_base/2).
:- dynamic(hex_neb_base/2).

/* All board hexagons */
hex_type_base(hex_g71,mine).
hex_type_base(hex_m61,mountain).
hex_type_base(hex_m62,mountain).
hex_type_base(hex_m63,mountain).
hex_type_base(hex_f51,forest).
hex_type_base(hex_f52,forest).
hex_type_base(hex_f53,forest).
hex_type_base(hex_f54,forest).
hex_type_base(hex_f55,forest).
hex_type_base(hex_r41,river).
hex_type_base(hex_r42,river).
hex_type_base(hex_r43,river).
hex_type_base(hex_r44,river).
hex_type_base(hex_r45,river).
hex_type_base(hex_r46,river).
hex_type_base(hex_r47,river).
hex_type_base(hex_p31,prairie).
hex_type_base(hex_p32,prairie).
hex_type_base(hex_p33,prairie).
hex_type_base(hex_p34,prairie).
hex_type_base(hex_p35,prairie).
hex_type_base(hex_p36,prairie).
hex_type_base(hex_p37,prairie).
hex_type_base(hex_p38,prairie).
hex_type_base(hex_p39,prairie).
hex_type_base(hex_p21,prairie).
hex_type_base(hex_p22,prairie).
hex_type_base(hex_p23,prairie).
hex_type_base(hex_p24,prairie).
hex_type_base(hex_p25,prairie).
hex_type_base(hex_p26,prairie).
hex_type_base(hex_p27,prairie).
hex_type_base(hex_p28,prairie).
hex_type_base(hex_p29,prairie).
hex_type_base(hex_p2a,prairie).
hex_type_base(hex_p2b,prairie).
hex_type_base(hex_p11,prairie).
hex_type_base(hex_p12,prairie).
hex_type_base(hex_p13,prairie).
hex_type_base(hex_p14,prairie).
hex_type_base(hex_p15,prairie).
hex_type_base(hex_v1,village).
hex_type_base(hex_v2,village).
hex_type_base(hex_v3,village).
hex_type_base(hex_v4,village).
hex_type_base(hex_v5,village).
hex_type_base(hex_v6,village).

/* All hexagons height (aka distance the from board bottom) */
hex_height_base(hex_g71,7).
hex_height_base(hex_m61,6).
hex_height_base(hex_m62,6).
hex_height_base(hex_m63,6).
hex_height_base(hex_f51,5).
hex_height_base(hex_f52,5).
hex_height_base(hex_f53,5).
hex_height_base(hex_f54,5).
hex_height_base(hex_f55,5).
hex_height_base(hex_r41,4).
hex_height_base(hex_r42,4).
hex_height_base(hex_r43,4).
hex_height_base(hex_r44,4).
hex_height_base(hex_r45,4).
hex_height_base(hex_r46,4).
hex_height_base(hex_r47,4).
hex_height_base(hex_p31,3).
hex_height_base(hex_p32,3).
hex_height_base(hex_p33,3).
hex_height_base(hex_p34,3).
hex_height_base(hex_p35,3).
hex_height_base(hex_p36,3).
hex_height_base(hex_p37,3).
hex_height_base(hex_p38,3).
hex_height_base(hex_p39,3).
hex_height_base(hex_p21,2).
hex_height_base(hex_p22,2).
hex_height_base(hex_p23,2).
hex_height_base(hex_p24,2).
hex_height_base(hex_p25,2).
hex_height_base(hex_p26,2).
hex_height_base(hex_p27,2).
hex_height_base(hex_p28,2).
hex_height_base(hex_p29,2).
hex_height_base(hex_p2a,2).
hex_height_base(hex_p2b,2).
hex_height_base(hex_p11,1).
hex_height_base(hex_p12,1).
hex_height_base(hex_p13,1).
hex_height_base(hex_p14,1).
hex_height_base(hex_p15,1).
hex_height_base(hex_v1,1).
hex_height_base(hex_v2,1).
hex_height_base(hex_v3,1).
hex_height_base(hex_v4,1).
hex_height_base(hex_v5,1).
hex_height_base(hex_v6,1).

/* All hexagons neighbors */
hex_neb_base(hex_g71,hex_m61).
hex_neb_base(hex_g71,hex_m62).
hex_neb_base(hex_g71,hex_m63).
hex_neb_base(hex_m61,hex_g71).
hex_neb_base(hex_m61,hex_m62).
hex_neb_base(hex_m61,hex_f51).
hex_neb_base(hex_m61,hex_f52).
hex_neb_base(hex_m62,hex_g71).
hex_neb_base(hex_m62,hex_m61).
hex_neb_base(hex_m62,hex_m63).
hex_neb_base(hex_m62,hex_f52).
hex_neb_base(hex_m62,hex_f53).
hex_neb_base(hex_m62,hex_f54).
hex_neb_base(hex_m63,hex_g71).
hex_neb_base(hex_m63,hex_m62).
hex_neb_base(hex_m63,hex_f54).
hex_neb_base(hex_m63,hex_f55).
hex_neb_base(hex_f51,hex_m61).
hex_neb_base(hex_f51,hex_f52).
hex_neb_base(hex_f51,hex_r41).
hex_neb_base(hex_f51,hex_r42).
hex_neb_base(hex_f52,hex_m61).
hex_neb_base(hex_f52,hex_m62).
hex_neb_base(hex_f52,hex_f51).
hex_neb_base(hex_f52,hex_f53).
hex_neb_base(hex_f52,hex_r42).
hex_neb_base(hex_f52,hex_r43).
hex_neb_base(hex_f53,hex_m62).
hex_neb_base(hex_f53,hex_f52).
hex_neb_base(hex_f53,hex_f54).
hex_neb_base(hex_f53,hex_r43).
hex_neb_base(hex_f53,hex_r44).
hex_neb_base(hex_f53,hex_r45).
hex_neb_base(hex_f54,hex_m62).
hex_neb_base(hex_f54,hex_m63).
hex_neb_base(hex_f54,hex_f53).
hex_neb_base(hex_f54,hex_f55).
hex_neb_base(hex_f54,hex_r45).
hex_neb_base(hex_f54,hex_r46).
hex_neb_base(hex_f55,hex_m63).
hex_neb_base(hex_f55,hex_f54).
hex_neb_base(hex_f55,hex_r46).
hex_neb_base(hex_f55,hex_r47).
hex_neb_base(hex_r41,hex_f51).
hex_neb_base(hex_r41,hex_r42).
hex_neb_base(hex_r41,hex_p31).
hex_neb_base(hex_r41,hex_p32).
hex_neb_base(hex_r42,hex_f51).
hex_neb_base(hex_r42,hex_f52).
hex_neb_base(hex_r42,hex_r41).
hex_neb_base(hex_r42,hex_r43).
hex_neb_base(hex_r42,hex_p32).
hex_neb_base(hex_r42,hex_p33).
hex_neb_base(hex_r43,hex_f52).
hex_neb_base(hex_r43,hex_f53).
hex_neb_base(hex_r43,hex_r42).
hex_neb_base(hex_r43,hex_r44).
hex_neb_base(hex_r43,hex_p33).
hex_neb_base(hex_r43,hex_p34).
hex_neb_base(hex_r44,hex_f53).
hex_neb_base(hex_r44,hex_r43).
hex_neb_base(hex_r44,hex_r45).
hex_neb_base(hex_r44,hex_p34).
hex_neb_base(hex_r44,hex_p35).
hex_neb_base(hex_r44,hex_p36).
hex_neb_base(hex_r45,hex_f53).
hex_neb_base(hex_r45,hex_f54).
hex_neb_base(hex_r45,hex_r44).
hex_neb_base(hex_r45,hex_r46).
hex_neb_base(hex_r45,hex_p36).
hex_neb_base(hex_r45,hex_p37).
hex_neb_base(hex_r46,hex_f54).
hex_neb_base(hex_r46,hex_f55).
hex_neb_base(hex_r46,hex_r45).
hex_neb_base(hex_r46,hex_r47).
hex_neb_base(hex_r46,hex_p37).
hex_neb_base(hex_r46,hex_p38).
hex_neb_base(hex_r47,hex_f55).
hex_neb_base(hex_r47,hex_r46).
hex_neb_base(hex_r47,hex_p38).
hex_neb_base(hex_r47,hex_p39).
hex_neb_base(hex_p31,hex_r41).
hex_neb_base(hex_p31,hex_p32).
hex_neb_base(hex_p31,hex_p21).
hex_neb_base(hex_p31,hex_p22).
hex_neb_base(hex_p32,hex_r41).
hex_neb_base(hex_p32,hex_r42).
hex_neb_base(hex_p32,hex_p31).
hex_neb_base(hex_p32,hex_p33).
hex_neb_base(hex_p32,hex_p22).
hex_neb_base(hex_p32,hex_p23).
hex_neb_base(hex_p33,hex_r42).
hex_neb_base(hex_p33,hex_r43).
hex_neb_base(hex_p33,hex_p32).
hex_neb_base(hex_p33,hex_p34).
hex_neb_base(hex_p33,hex_p23).
hex_neb_base(hex_p33,hex_p24).
hex_neb_base(hex_p34,hex_r43).
hex_neb_base(hex_p34,hex_r44).
hex_neb_base(hex_p34,hex_p33).
hex_neb_base(hex_p34,hex_p35).
hex_neb_base(hex_p34,hex_p24).
hex_neb_base(hex_p34,hex_p25).
hex_neb_base(hex_p35,hex_r44).
hex_neb_base(hex_p35,hex_p34).
hex_neb_base(hex_p35,hex_p36).
hex_neb_base(hex_p35,hex_p25).
hex_neb_base(hex_p35,hex_p26).
hex_neb_base(hex_p35,hex_p27).
hex_neb_base(hex_p36,hex_r44).
hex_neb_base(hex_p36,hex_r45).
hex_neb_base(hex_p36,hex_p35).
hex_neb_base(hex_p36,hex_p37).
hex_neb_base(hex_p36,hex_p27).
hex_neb_base(hex_p36,hex_p28).
hex_neb_base(hex_p37,hex_r45).
hex_neb_base(hex_p37,hex_r46).
hex_neb_base(hex_p37,hex_p36).
hex_neb_base(hex_p37,hex_p38).
hex_neb_base(hex_p37,hex_p28).
hex_neb_base(hex_p37,hex_p29).
hex_neb_base(hex_p38,hex_r46).
hex_neb_base(hex_p38,hex_r47).
hex_neb_base(hex_p38,hex_p37).
hex_neb_base(hex_p38,hex_p39).
hex_neb_base(hex_p38,hex_p29).
hex_neb_base(hex_p38,hex_p2a).
hex_neb_base(hex_p39,hex_r47).
hex_neb_base(hex_p39,hex_p38).
hex_neb_base(hex_p39,hex_p2a).
hex_neb_base(hex_p39,hex_p2b).
hex_neb_base(hex_p21,hex_p31).
hex_neb_base(hex_p21,hex_p22).
hex_neb_base(hex_p21,hex_v1).
hex_neb_base(hex_p22,hex_p31).
hex_neb_base(hex_p22,hex_p32).
hex_neb_base(hex_p22,hex_p21).
hex_neb_base(hex_p22,hex_p23).
hex_neb_base(hex_p22,hex_p11).
hex_neb_base(hex_p22,hex_v1).
hex_neb_base(hex_p23,hex_p32).
hex_neb_base(hex_p23,hex_p33).
hex_neb_base(hex_p23,hex_p22).
hex_neb_base(hex_p23,hex_p24).
hex_neb_base(hex_p23,hex_p11).
hex_neb_base(hex_p23,hex_v2).
hex_neb_base(hex_p24,hex_p33).
hex_neb_base(hex_p24,hex_p34).
hex_neb_base(hex_p24,hex_p23).
hex_neb_base(hex_p24,hex_p25).
hex_neb_base(hex_p24,hex_p12).
hex_neb_base(hex_p24,hex_v2).
hex_neb_base(hex_p25,hex_p34).
hex_neb_base(hex_p25,hex_p35).
hex_neb_base(hex_p25,hex_p24).
hex_neb_base(hex_p25,hex_p26).
hex_neb_base(hex_p25,hex_p12).
hex_neb_base(hex_p25,hex_v3).
hex_neb_base(hex_p26,hex_p35).
hex_neb_base(hex_p26,hex_p25).
hex_neb_base(hex_p26,hex_p27).
hex_neb_base(hex_p26,hex_p13).
hex_neb_base(hex_p26,hex_v3).
hex_neb_base(hex_p26,hex_v4).
hex_neb_base(hex_p27,hex_p35).
hex_neb_base(hex_p27,hex_p36).
hex_neb_base(hex_p27,hex_p26).
hex_neb_base(hex_p27,hex_p28).
hex_neb_base(hex_p27,hex_p14).
hex_neb_base(hex_p27,hex_v4).
hex_neb_base(hex_p28,hex_p36).
hex_neb_base(hex_p28,hex_p37).
hex_neb_base(hex_p28,hex_p27).
hex_neb_base(hex_p28,hex_p29).
hex_neb_base(hex_p28,hex_p14).
hex_neb_base(hex_p28,hex_v5).
hex_neb_base(hex_p29,hex_p37).
hex_neb_base(hex_p29,hex_p38).
hex_neb_base(hex_p29,hex_p28).
hex_neb_base(hex_p29,hex_p2a).
hex_neb_base(hex_p29,hex_p15).
hex_neb_base(hex_p29,hex_v5).
hex_neb_base(hex_p2a,hex_p38).
hex_neb_base(hex_p2a,hex_p39).
hex_neb_base(hex_p2a,hex_p29).
hex_neb_base(hex_p2a,hex_p2b).
hex_neb_base(hex_p2a,hex_p15).
hex_neb_base(hex_p2a,hex_v6).
hex_neb_base(hex_p2b,hex_p39).
hex_neb_base(hex_p2b,hex_p2a).
hex_neb_base(hex_p2b,hex_v6).
hex_neb_base(hex_p11,hex_p22).
hex_neb_base(hex_p11,hex_p23).
hex_neb_base(hex_p11,hex_v1).
hex_neb_base(hex_p11,hex_v2).
hex_neb_base(hex_p12,hex_p24).
hex_neb_base(hex_p12,hex_p25).
hex_neb_base(hex_p12,hex_v2).
hex_neb_base(hex_p12,hex_v3).
hex_neb_base(hex_p13,hex_p26).
hex_neb_base(hex_p13,hex_v3).
hex_neb_base(hex_p13,hex_v4).
hex_neb_base(hex_p14,hex_p27).
hex_neb_base(hex_p14,hex_p28).
hex_neb_base(hex_p14,hex_v4).
hex_neb_base(hex_p14,hex_v5).
hex_neb_base(hex_p15,hex_p29).
hex_neb_base(hex_p15,hex_p2a).
hex_neb_base(hex_p15,hex_v5).
hex_neb_base(hex_p15,hex_v6).
hex_neb_base(hex_v1,hex_p21).
hex_neb_base(hex_v1,hex_p22).
hex_neb_base(hex_v1,hex_p11).
hex_neb_base(hex_v2,hex_p23).
hex_neb_base(hex_v2,hex_p24).
hex_neb_base(hex_v2,hex_p11).
hex_neb_base(hex_v2,hex_p12).
hex_neb_base(hex_v3,hex_p25).
hex_neb_base(hex_v3,hex_p26).
hex_neb_base(hex_v3,hex_p12).
hex_neb_base(hex_v3,hex_p13).
hex_neb_base(hex_v4,hex_p26).
hex_neb_base(hex_v4,hex_p27).
hex_neb_base(hex_v4,hex_p13).
hex_neb_base(hex_v4,hex_p14).
hex_neb_base(hex_v5,hex_p28).
hex_neb_base(hex_v5,hex_p29).
hex_neb_base(hex_v5,hex_p14).
hex_neb_base(hex_v5,hex_p15).
hex_neb_base(hex_v6,hex_p2a).
hex_neb_base(hex_v6,hex_p2b).
hex_neb_base(hex_v6,hex_p15).

hex_limit(hex_p11,left,1).
hex_limit(hex_p15,right,1).
hex_limit(hex_p21,left,2).
hex_limit(hex_p2b,right,2).
hex_limit(hex_p31,left,3).
hex_limit(hex_p39,right,3).
hex_limit(hex_r41,left,4).
hex_limit(hex_r47,right,4).
hex_limit(hex_f51,left,5).
hex_limit(hex_f55,right,5).
hex_limit(hex_m61,left,6).
hex_limit(hex_m63,right,6).
hex_limit(hex_g71,left,7).
hex_limit(hex_g71,right,7).
