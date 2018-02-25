LimiterOffsetOut {

  *ar { |out, in, amp=0.9|
    ^OffsetOut.ar(out, Limiter.ar(in, amp));
  }

}


BasicGlitch {

  *ar {
    |in, st_length=1, st_rate=1, st_maxdelay=10, crush=1, bits=24, pitch=1|
    ^PitchShift.ar(BinFreeze.ar(Decimator.ar(Stutter.ar(in, 1, st_length, st_rate, st_maxdelay)!2, 44100/crush, bits), 256, 2, 1), pitchRatio:pitch);
  }

}

