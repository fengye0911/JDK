/*
 * Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.marlin;

/**
 * Marlin constant holder using System properties
 */
public interface MarlinConst {
    // enable Logs (logger or stdout)
    static final boolean ENABLE_LOGS = MarlinProperties.isLoggingEnabled();
    // use Logger instead of stdout
    static final boolean USE_LOGGER = ENABLE_LOGS && MarlinProperties.isUseLogger();

    // log new RendererContext
    static final boolean LOG_CREATE_CONTEXT = ENABLE_LOGS
        && MarlinProperties.isLogCreateContext();
    // log misc.Unsafe alloc/realloc/free
    static final boolean LOG_UNSAFE_MALLOC = ENABLE_LOGS
        && MarlinProperties.isLogUnsafeMalloc();
    // do check unsafe alignment:
    static final boolean DO_CHECK_UNSAFE = false;

    // do statistics
    static final boolean DO_STATS = ENABLE_LOGS && MarlinProperties.isDoStats();
    // do monitors
    // disabled to reduce byte-code size a bit...
    static final boolean DO_MONITORS = false;
//    static final boolean DO_MONITORS = ENABLE_LOGS && MarlinProperties.isDoMonitors();
    // do checks
    static final boolean DO_CHECKS = ENABLE_LOGS && MarlinProperties.isDoChecks();

    // do AA range checks: disable when algorithm / code is stable
    static final boolean DO_AA_RANGE_CHECK = false;

    // enable logs
    static final boolean DO_LOG_WIDEN_ARRAY = ENABLE_LOGS && false;
    // enable oversize logs
    static final boolean DO_LOG_OVERSIZE = ENABLE_LOGS && false;
    // enable traces
    static final boolean DO_TRACE = ENABLE_LOGS && false;

    // do flush stats
    static final boolean DO_FLUSH_STATS = true;
    // do flush monitors
    static final boolean DO_FLUSH_MONITORS = true;
    // use one polling thread to dump statistics/monitors
    static final boolean USE_DUMP_THREAD = false;
    // thread dump interval (ms)
    static final long DUMP_INTERVAL = 5000L;

    // do clean dirty array
    static final boolean DO_CLEAN_DIRTY = false;

    // flag to use line simplifier
    static final boolean USE_SIMPLIFIER = MarlinProperties.isUseSimplifier();

    // flag to enable logs related bounds checks
    static final boolean DO_LOG_BOUNDS = ENABLE_LOGS && false;

    // Initial Array sizing (initial context capacity) ~ 450K

    // 2048 pixel (width x height) for initial capacity
    static final int INITIAL_PIXEL_DIM
        = MarlinProperties.getInitialImageSize();

    // typical array sizes: only odd numbers allowed below
    static final int INITIAL_ARRAY        = 256;

    // alpha row dimension
    static final int INITIAL_AA_ARRAY     = INITIAL_PIXEL_DIM;

    // 4096 edges for initial capacity
    static final int INITIAL_EDGES_COUNT = MarlinProperties.getInitialEdges();

    // initial edges = edges count (4096)
    // 6 ints per edges = 24 bytes
    // edges capacity = 24 x initial edges = 24 * edges count (4096) = 96K
    static final int INITIAL_EDGES_CAPACITY = INITIAL_EDGES_COUNT * 24;

    // crossing capacity = edges count / 4 ~ 1024
    static final int INITIAL_CROSSING_COUNT = INITIAL_EDGES_COUNT >> 2;

    // zero value as byte
    static final byte BYTE_0 = (byte) 0;

    // subpixels expressed as log2
    public static final int SUBPIXEL_LG_POSITIONS_X
        = MarlinProperties.getSubPixel_Log2_X();
    public static final int SUBPIXEL_LG_POSITIONS_Y
        = MarlinProperties.getSubPixel_Log2_Y();

    // number of subpixels
    public static final int SUBPIXEL_POSITIONS_X = 1 << (SUBPIXEL_LG_POSITIONS_X);
    public static final int SUBPIXEL_POSITIONS_Y = 1 << (SUBPIXEL_LG_POSITIONS_Y);

    // 2048 (pixelSize) pixels (height) x 8 subpixels = 64K
    static final int INITIAL_BUCKET_ARRAY
        = INITIAL_PIXEL_DIM * SUBPIXEL_POSITIONS_Y;

    public static final float NORM_SUBPIXELS
        = (float) Math.sqrt(( SUBPIXEL_POSITIONS_X * SUBPIXEL_POSITIONS_X
                            + SUBPIXEL_POSITIONS_Y * SUBPIXEL_POSITIONS_Y) / 2.0d);

    public static final int MAX_AA_ALPHA
        = SUBPIXEL_POSITIONS_X * SUBPIXEL_POSITIONS_Y;

    public static final int BLOCK_SIZE_LG = MarlinProperties.getBlockSize_Log2();
    public static final int BLOCK_SIZE    = 1 << BLOCK_SIZE_LG;

    static final boolean ENABLE_BLOCK_FLAGS = MarlinProperties.isUseTileFlags();
    static final boolean ENABLE_BLOCK_FLAGS_HEURISTICS = MarlinProperties.isUseTileFlagsWithHeuristics();

    static final boolean FORCE_RLE = MarlinProperties.isForceRLE();
    static final boolean FORCE_NO_RLE = MarlinProperties.isForceNoRLE();
    // minimum width to try using RLE encoding:
    static final int RLE_MIN_WIDTH
        = Math.max(BLOCK_SIZE, MarlinProperties.getRLEMinWidth());

    // Constants
    public static final int WIND_EVEN_ODD = 0;
    public static final int WIND_NON_ZERO = 1;

    /**
     * Constant value for join style.
     */
    public static final int JOIN_MITER = 0;

    /**
     * Constant value for join style.
     */
    public static final int JOIN_ROUND = 1;

    /**
     * Constant value for join style.
     */
    public static final int JOIN_BEVEL = 2;

    /**
     * Constant value for end cap style.
     */
    public static final int CAP_BUTT = 0;

    /**
     * Constant value for end cap style.
     */
    public static final int CAP_ROUND = 1;

    /**
     * Constant value for end cap style.
     */
    public static final int CAP_SQUARE = 2;

    // Out codes
    static final int OUTCODE_TOP      = 1;
    static final int OUTCODE_BOTTOM   = 2;
    static final int OUTCODE_LEFT     = 4;
    static final int OUTCODE_RIGHT    = 8;
    static final int OUTCODE_MASK_T_B = OUTCODE_TOP  | OUTCODE_BOTTOM;
    static final int OUTCODE_MASK_L_R = OUTCODE_LEFT | OUTCODE_RIGHT;
    static final int OUTCODE_MASK_T_B_L_R = OUTCODE_MASK_T_B | OUTCODE_MASK_L_R;
}
